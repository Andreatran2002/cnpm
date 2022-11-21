package com.onlinestorewepr.service;

import com.onlinestorewepr.dao.CartDAO;
import com.onlinestorewepr.dao.CartDAO;
import com.onlinestorewepr.dao.UserDAO;
import com.onlinestorewepr.entity.Cart;

public class CartService {
    private CartDAO cartDAO;
    private UserDAO userDAO;
    private ServiceResult serviceResult;

    public ServiceResult getServiceResult() {
        return serviceResult;
    }

    public void setServiceResult(ServiceResult serviceResult) {
        this.serviceResult = serviceResult;
    }

    public CartService() {
        userDAO = new UserDAO();
        cartDAO = new CartDAO();
        serviceResult= new ServiceResult();
    }

    public void createCart(Integer userId) {
        String message, messageType;

        // Check if a cart with the same name already exists in DB
        if (cartDAO.findByUserId(userId) == null) {
            try {
                Cart cart = new Cart(0, userDAO.getByID(userId));
                cartDAO.insert(cart);

                message = "A new cart was created successfully!";
                messageType = "success";
            } catch (Exception e) {
                e.printStackTrace();
                message = "An error occurred when creating a new cart! Please try again.";
                messageType = "danger";
            }
        } else {
            message = String.format("A cart with userid %s already exists! Please choose another user", userId);
            messageType = "danger";
        }

        serviceResult.setMessage(message);
        serviceResult.setMessageType(messageType);
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
    public Cart getCartByUser(int userid){
        return cartDAO.findByUserId(userid);
    }
}
