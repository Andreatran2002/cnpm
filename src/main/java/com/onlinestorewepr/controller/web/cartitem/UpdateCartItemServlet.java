package com.onlinestorewepr.controller.web.cartitem;


import com.onlinestorewepr.service.CartItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cart-item/update")
public class UpdateCartItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CartItemService cartService = new CartItemService(req,resp);
        String id = req.getParameter("id");
        if (id != null) {
            cartService.updateCartItem(Integer.parseInt(id));
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);
    }
}