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
        boolean hasErr = false;

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
            req.getRequestDispatcher("/admin/login.jsp").forward(req,resp);
        }
        else {
            HttpSession session = req.getSession();
            session.setAttribute("adminLogged",user);
            session.setMaxInactiveInterval(1000);
            req.getRequestDispatcher("/admin/products").forward(req,resp);
        }
    }
}
