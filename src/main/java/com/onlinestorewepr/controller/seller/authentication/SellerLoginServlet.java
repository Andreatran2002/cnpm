package com.onlinestorewepr.controller.seller.authentication;

import com.onlinestorewepr.service.AdminService;
import com.onlinestorewepr.service.SellerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SellerLoginServlet",value = {"/login-seller","/seller/login-seller"})
public class SellerLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/seller/login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SellerService sellerServiceLogin = new SellerService(req,resp);
        sellerServiceLogin.sellerLogin();
    }
}
