package com.onlinestorewepr.controller.web.cartitem;


import com.onlinestorewepr.entity.Cart;
import com.onlinestorewepr.entity.User;
import com.onlinestorewepr.service.CartItemService;
import com.onlinestorewepr.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cart-item/add")
public class AddCartItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CartItemService cartService = new CartItemService(req,resp);
        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("userLogged");
        System.out.println("Cart-item : "+user.getUsername());

        cartService.createCartItem(user.getUsername());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);
    }
}