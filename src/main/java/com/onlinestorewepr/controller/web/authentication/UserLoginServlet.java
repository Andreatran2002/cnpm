package com.onlinestorewepr.controller.web.authentication;

import com.onlinestorewepr.dao.UserDAO;
import com.onlinestorewepr.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = {"/login"})
public class UserLoginServlet extends HttpServlet {
    private UserDAO loginDao;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookies[] = request.getCookies();
       if (cookies !=null){
           for(Cookie i: cookies){
               if(i.getName().equals("username")){
                   request.setAttribute("username",i.getValue());
                   System.out.print(i.getValue());
               }
               if(i.getName().equals("password")){
                   request.setAttribute("password",i.getValue());
                   System.out.print(i.getValue());
               }
           }
       }
        if (request.getSession().getAttribute("userLogged") != null) {
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            request.getRequestDispatcher("/web/authentication.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userServiceLogin = new UserService(req,resp);
        userServiceLogin.login();
    }
}
