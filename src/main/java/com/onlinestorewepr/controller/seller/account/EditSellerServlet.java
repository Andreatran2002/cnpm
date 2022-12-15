package com.onlinestorewepr.controller.seller.account;

import com.onlinestorewepr.service.SellerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value={"/seller/accounts/update"})
public class EditSellerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SellerService sellerService = new SellerService(req,resp);
        sellerService.ShowEditSeller();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SellerService sellerServiceS = new SellerService(req,resp);
        sellerServiceS.EditSeller();

    }
}
