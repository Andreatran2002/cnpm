package com.onlinestorewepr.controller.web.cart;

import com.onlinestorewepr.dao.CartDAO;
import com.onlinestorewepr.entity.Cart;
import com.onlinestorewepr.entity.Order;
import com.onlinestorewepr.service.CartService;
import com.onlinestorewepr.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    CartDAO cartDAO = new CartDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cart cart = cartDAO.get(1);

        req.setAttribute("cart", cart);
        req.getRequestDispatcher("/web/shopping-cart.jsp").forward(req, resp);
    }

}
