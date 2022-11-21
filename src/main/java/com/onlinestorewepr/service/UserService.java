package com.onlinestorewepr.service;

import com.onlinestorewepr.dao.UserDAO;
import com.onlinestorewepr.entity.Category;
import com.onlinestorewepr.entity.Product;
import com.onlinestorewepr.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class UserService {
    private UserDAO userDAO = new UserDAO();
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
        if (user != null) {
            if (password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }


    public void userRegister() throws IOException, ServletException {
        String name = req.getParameter("fullName");
        String username = req.getParameter("usernameNew");
        String password = req.getParameter("passwordNew");
        String phone =req.getParameter("phone");
        String gender = req.getParameter("gender");

        User userCreated = userDAO.findUserCreated(username);
        String message = "";
        if(name==null || phone==null ||username == null ||password==null || username.length()==0 || password.length()==0){
            message ="Vui lòng nhập Đầy đủ thông tin Đăng ký!";
            req.setAttribute("messageRegisterFail",message);
            req.getRequestDispatcher("/web/authentication.jsp").forward(req,resp);
        }
//      Kiểm tra xem có tồn tại hay chưa
        else if(userCreated!=null){
            message= "Tên tài khoản đã tồn tại!";
            req.setAttribute("messageRegisterFail",message);
            req.getRequestDispatcher("/web/authentication.jsp").forward(req,resp);
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
            if (message != null) {
                req.setAttribute("messageRegisterSuccess", message);
                req.getRequestDispatcher("/web/authentication.jsp").forward(req,resp);
            }
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

        if(username == null ||password==null || username.length()==0 || password.length()==0){
            hasErr= true;
            errMessage ="Vui lòng nhập Tên đăng nhập & Mật khẩu";
        }
        else {
            try{
                if (user == null)
                {
                    hasErr = true;
                    errMessage ="Tên đăng nhập hoặc mật khẩu không đúng!";
                }
            }
            catch (Exception e){
                e.printStackTrace();
                hasErr = true;
                errMessage = e.getMessage();
            }
        }
        if(hasErr)
        {
            req.setAttribute("message",errMessage);
            req.getRequestDispatcher("/web/authentication.jsp").forward(req,resp);
        }
        else {
            HttpSession session = req.getSession();
            session.setAttribute("userLogged",user);
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
//        resp.sendRedirect(req.getContextPath()+"/web/profile.jsp");
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

    public void updateUserProfile() throws ServletException, IOException{
        resp.setContentType("text/html;charset=UTF-8");
        User user = (User) req.getSession().getAttribute("userLogged");
        editUserProfile(user);
        userDAO.update(user);
        showProfile();
    }

}
