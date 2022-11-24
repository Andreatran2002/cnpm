package com.onlinestorewepr.service;

import com.onlinestorewepr.dao.UserDAO;
import com.onlinestorewepr.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class UserService {
    private UserDAO userDAO;
    private ServiceResult serviceResult;

    HttpServletResponse resp;
    HttpServletRequest req;

    public UserService(HttpServletRequest req, HttpServletResponse resp)
    {
        this.req = req;
        this.resp = resp;
        userDAO = new UserDAO();
    }

    public User authenticate(String username, String password) {
        User user = userDAO.get(username);
        if (user != null && !user.isAdmin()) {
            if (password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public void userRegister() throws IOException,ServletException{
        String name = req.getParameter("fullName");
        String username = req.getParameter("usernameNew");
        String password = req.getParameter("passwordNew");
        String retypePass = req.getParameter("passwordNewRetype");
        String phone =req.getParameter("phone");
        String gender = req.getParameter("gender");

        User userCreated = userDAO.findUserCreated(username);
        String message = "";
        boolean hasErr = false;
        if(name==null || phone==null ||username == null ||password==null || username.length()==0 || password.length()==0){
            message ="Vui lòng nhập Đầy đủ thông tin Đăng ký!";
            hasErr = true;
        }
//      Kiểm tra xem có tồn tại hay chưa
        else if(userCreated!=null){
            message= "Tên tài khoản đã tồn tại!";
            hasErr = true;
        }
        else if(!Objects.equals(retypePass, password)){
            message = "Vui lòng nhập Password trùng với nhau!";
            hasErr = true;
        }
        else {
            User userNew = new User();
            if(username != null){
                userNew.setUsername(username);
            }
            if(password!= null && !password.equals("")){
                userNew.setPassword(password);
            }
            userNew.setName(name);
            userNew.setGender(gender);
            userNew.setPhone(phone);
            userDAO.insert(userNew);
            message= "Tạo thành công! Đăng nhập để bắt đầu";
        }
        //Check Error
        if(hasErr)
        {
            req.setAttribute("messageRegisterFail",message);
            req.getRequestDispatcher("/web/authentication.jsp").forward(req,resp);
        }
        else {
            req.setAttribute("messageRegisterSuccess", message);
            req.getRequestDispatcher("/web/authentication.jsp").forward(req,resp);
        }
    }

    protected  void saveRememberMe(HttpServletResponse response, String userName, String password){
        Cookie username = new Cookie("username",userName);
        Cookie pass = new Cookie("password",password);
        username.setMaxAge(60 * 60 * 24 * 365 * 3);
        pass.setMaxAge(60 * 60 * 24 * 365 * 3);
        response.addCookie(username);
        response.addCookie(pass);
        response.setContentType("text/html");
    }

    public void login() throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");
        String errMessage = "";
        boolean hasErr = false;

        User user = authenticate(username, password);
        boolean isRememberMe = "on".equals(remember);

        //Check login
        if(username == null ||password==null || username.length()==0 || password.length()==0){
            hasErr= true;
            errMessage ="Tên đăng nhập & Mật khẩu không được trống!";
        }
        else {
            try{
                if (user == null)
                {
                    hasErr = true;
                    errMessage ="Tên Đăng nhập hoặc Mật khẩu không đúng!";
                }
            }
            catch (Exception e){
                e.printStackTrace();
                hasErr = true;
                errMessage = e.getMessage();
            }
        }

        //Display status on web
        if(hasErr)
        {
            req.setAttribute("message",errMessage);
            req.getRequestDispatcher("/web/authentication.jsp").forward(req,resp);
        }
        else {
            //Create session when login is successful
            HttpSession session = req.getSession();
            session.setAttribute("userLogged",user);
            //Check Remember me to create Cookie
            if(isRememberMe)
            {
                saveRememberMe(resp,username,password);
            }
            Object objRedirectURL = session.getAttribute("redirectURL");
            session.setMaxInactiveInterval(1000);
            if (objRedirectURL != null) {
                String redirectURL = (String) objRedirectURL;
                session.removeAttribute("redirectURL");
                resp.sendRedirect(redirectURL);
            } else {
                showProfile();
            }
        }
    }

    public void showProfile() throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.getRequestDispatcher("/web/profile.jsp").forward(req,resp);
    }

    public void showEditUserProfile() throws ServletException, IOException{
        resp.setContentType("text/html;charset=UTF-8");
        req.getRequestDispatcher("/web/edit-profile.jsp").forward(req,resp);
    }

    public void editUserProfile(User user){
        String fullName = req.getParameter("name");
        String phone = req.getParameter("phone");
        String gender = req.getParameter("sex");
        String address = req.getParameter("address");

        user.setName(fullName);
        user.setPhone(phone);
        user.setGender(gender);
        user.setAddress(address);
    }
    public void editAdminProfile(User user){
        String username = req.getParameter("username");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String gender = req.getParameter("gender");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");

        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setPhone(phone);
        user.setGender(gender);
        user.setAddress(address);
    }

    public void updateUserProfile() throws ServletException, IOException{
        resp.setContentType("text/html;charset=UTF-8");
        User user = (User) req.getSession().getAttribute("userLogged");
        editUserProfile(user);
        userDAO.update(user);
        showProfile();
    }
    public void updateAdminProfile() throws ServletException, IOException{
        resp.setContentType("text/html;charset=UTF-8");
        User user = (User) req.getSession().getAttribute("adminLogged");
        editAdminProfile(user);
        userDAO.update(user);
        req.getRequestDispatcher("/admin/account-profile.jsp").forward(req,resp);
    }
    public List<User> getAllUser(){
        List<User> users = null;
        users = userDAO.getAll();
        return users;
    }
}
