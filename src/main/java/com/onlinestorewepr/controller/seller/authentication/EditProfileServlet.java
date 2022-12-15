package com.onlinestorewepr.controller.seller.authentication;

import com.onlinestorewepr.service.AdminService;
import com.onlinestorewepr.service.SellerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/seller/edit-seller-profile","/edit-seller-profile"})
public class EditProfileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        SellerService sellerService = new SellerService(req,resp);
        sellerService.updateSellerProfile();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("/seller/account-profile.jsp").forward(req,resp);
    }
}
