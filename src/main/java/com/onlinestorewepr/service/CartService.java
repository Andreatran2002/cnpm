package com.onlinestorewepr.service;

import com.onlinestorewepr.dao.CartDAO;
import com.onlinestorewepr.dao.CartDAO;
import com.onlinestorewepr.dao.UserDAO;
import com.onlinestorewepr.entity.Cart;
import com.onlinestorewepr.util.MessageUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CartService {
    private CartDAO cartDAO;
    private UserDAO userDAO;
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private MessageUtil message;
    private ServiceResult serviceResult;

    public ServiceResult getServiceResult() {
        return serviceResult;
    }

    public void setServiceResult(ServiceResult serviceResult) {
        this.serviceResult = serviceResult;
    }

    public CartService(HttpServletRequest req, HttpServletResponse resp) {
        userDAO = new UserDAO();
        cartDAO = new CartDAO();
        this.req = req;
        this.resp = resp;
        serviceResult= new ServiceResult();
    }

    public void CreateCart() {

//        String username = req.getParameter("username").trim();
//        String productId = req.getParameter("productid").trim();
//        String quantity = req.getParameter("quantity").trim();
//
//        if (username != null && productId!= null && quantity != null ){
//            Cart cart = cartDAO.findByUser(username);
//            if (cart!= null){
//                cartDAO.insert(new Cart() );
//
//            } else {
//                cartService.createCart(Integer.parseInt(userId));
//                cartItemService.createCartItem(cart.getId(),Integer.parseInt(productId), Integer.parseInt(quantity) );
//            }
//        }
//
//        out.println(cartItemService.getServiceResult());
    }

    public void deleteCart(int id) {
        Cart cart = cartDAO.get(id);
        String message, messageType;
        if (cart != null) {
            if (cart.getCartItems() == null || cart.getCartItems().isEmpty()) {
                try {
                    cartDAO.delete(id);
                    message = "Cart was deleted successfully!";
                    messageType = "primary";
                } catch (Exception e) {
                    e.printStackTrace();
                    message = "An error occurred when creating a new cart! Please try again.";
                    messageType = "danger";
                }
            } else {
                message = String.format("Could not delete the cart %s because it currently contains some item.", cart.getId());
                messageType = "danger";
            }
        } else {
            message = String.format("Cart with id %s does not exist", id);
            messageType = "danger";
        }

        serviceResult.setMessage(message);
        serviceResult.setMessageType(messageType);
    }

    public void updateCart(int id, int total) {
        Cart cart = cartDAO.get(id);
        String message = "", messageType = "";
        if (cart != null) {
            try {
                cart.setTotal(total);
                cartDAO.update(cart);

                message = "Cart's info was changed successfully!";
                messageType = "success";
            } catch (Exception e) {
                e.printStackTrace();
                message = "An error occurred when creating a new cart! Please try again.";
                messageType = "danger";
            }

        } else {
            message = String.format("Cart with id %s does not exist", id);
            messageType = "danger";
        }

        serviceResult.setMessage(message);
        serviceResult.setMessageType(messageType);
    }

    public Cart getCart(int id) {
        return cartDAO.get(id);
    }
    public void ShowCart() throws ServletException, IOException {
        String messageBody="",messageType="";
        String username = req.getParameter("username").trim();
        if (username != null ){
            Cart cart = cartDAO.findByUser(username);
            req.setAttribute("cart",cart);
            messageBody="Success";
            messageType="ok";

        }
        else{
            messageBody="Can't find user";
            messageType="danger";
        }
        message.setBody(messageBody);
        message.setType(messageType);

        req.setAttribute("action", "list");
        req.setAttribute("message", message);
        req.getRequestDispatcher("/web/shopping-cart.jsp").forward(req, resp);

    }


}
