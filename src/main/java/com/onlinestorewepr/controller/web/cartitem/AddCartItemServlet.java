package com.onlinestorewepr.controller.web.cartitem;


import com.onlinestorewepr.entity.Cart;
import com.onlinestorewepr.service.CartItemService;
import com.onlinestorewepr.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cart-item/add")
public class AddCartItemServlet extends HttpServlet {
    CartItemService cartItemService = new CartItemService();
    CartService cartService = new CartService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userid").trim();
        String productId = req.getParameter("productid").trim();
        String quantity = req.getParameter("quantity").trim();
        PrintWriter out = resp.getWriter();

        if (userId != null && productId!= null && quantity != null ){
            Cart cart = cartService.getCartByUser(Integer.parseInt(userId));
            if (cart!= null){
                cartItemService.createCartItem(cart.getId(),Integer.parseInt(productId), Integer.parseInt(quantity) );

            } else {
                cartService.createCart(Integer.parseInt(userId));
                cartItemService.createCartItem(cart.getId(),Integer.parseInt(productId), Integer.parseInt(quantity) );
            }
        }

        out.println(cartItemService.getServiceResult());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);
    }
}