package com.onlinestorewepr.controller.web.cart;


import com.onlinestorewepr.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cart/add")
public class AddCartServlet extends HttpServlet {
    CartService cartService = new CartService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userid").trim();
//        String productId = req.getParameter("productid").trim();
//        String quantity = req.getParameter("quantity").trim();

        if (userId != null ){
            cartService.createCart(Integer.parseInt(userId));
        }
        PrintWriter out = resp.getWriter();
        out.println(cartService.getServiceResult());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);
    }
}