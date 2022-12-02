package com.onlinestorewepr.service;

import com.onlinestorewepr.dao.UserDAO;
import com.onlinestorewepr.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminService {
    private UserDAO userDAO;
    private ServiceResult serviceResult;

    HttpServletResponse resp;
    HttpServletRequest req;

    public AdminService(HttpServletRequest req, HttpServletResponse resp)
    {
        this.req = req;
        this.resp = resp;
        userDAO = new UserDAO();
        serviceResult= new ServiceResult();
    }

    public User authenticate(String username, String password) {
        User user = userDAO.get(username);
        if (user != null && user.isAdmin()) {
            if (password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public void adminLogin() throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = authenticate(username,password);
        String errMessage = "";
        boolean hasError = false;

        if(username == null ||password==null || username.length()==0 || password.length()==0){
            hasError= true;
            errMessage ="Vui lòng nhập Tên đăng nhập & Mật khẩu!";
        }
        else {
            try{
                if (user == null)
                {
                    hasError = true;
                    errMessage ="Tên đăng nhập hoặc Mật khẩu không đúng!";
                }
            }
            catch (Exception e){
                e.printStackTrace();
                hasError = true;
                errMessage = e.getMessage();
            }
        }

        if(hasError)
        {
            req.setAttribute("message",errMessage);
            req.getRequestDispatcher("/admin/login.jsp").forward(req,resp);
        }
        else {
            HttpSession session = req.getSession();
            session.setAttribute("adminLogged",user);
            session.setMaxInactiveInterval(1000);
            req.getRequestDispatcher("/admin/index.jsp").forward(req,resp);
        }
    }

    public void updateAdminProfile() throws ServletException, IOException{
        resp.setContentType("text/html;charset=UTF-8");
        User user = (User) req.getSession().getAttribute("adminLogged");
        editAdminProfile(user);
        userDAO.update(user);
        req.getRequestDispatcher("/admin/account-profile.jsp").forward(req,resp);
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
}
