package com.onlinestorewepr.controller.web.checkout;

import com.onlinestorewepr.dao.CartDAO;
import com.onlinestorewepr.entity.Cart;
import com.onlinestorewepr.service.CartItemService;
import com.onlinestorewepr.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    CartDAO cartDAO = new CartDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cart cart = cartDAO.get(1);

        req.setAttribute("cart", cart);
        req.getRequestDispatcher("/web/checkout.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);
    }
}