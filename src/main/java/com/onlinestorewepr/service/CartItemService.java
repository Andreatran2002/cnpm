package com.onlinestorewepr.service;

import com.onlinestorewepr.dao.CartDAO;
import com.onlinestorewepr.dao.CartItemDAO;
import com.onlinestorewepr.dao.ProductDAO;
import com.onlinestorewepr.dao.UserDAO;
import com.onlinestorewepr.entity.CartItem;

public class CartItemService {
    private CartItemDAO cartItemDAO;
    private CartDAO cartDAO;
    private ProductDAO productDAO;
    private UserDAO userDAO;
    private ServiceResult serviceResult;

    public ServiceResult getServiceResult() {
        return serviceResult;
    }

    public void setServiceResult(ServiceResult serviceResult) {
        this.serviceResult = serviceResult;
    }

    public CartItemService() {
        userDAO = new UserDAO();
        cartDAO = new CartDAO();
        productDAO = new ProductDAO();
        cartItemDAO = new CartItemDAO();
        serviceResult= new ServiceResult();
    }

    public void createCartItem(int cartId, int productId, int quantity  ) {
        String message ="", messageType  ="";
        //check cartid and productid
        if (cartDAO.get(cartId) != null && productDAO.get(productId) != null) {
            if (cartItemDAO.findByProductId(cartId, productId) != null){
                CartItem item = cartItemDAO.findByProductId(cartId, productId) ;
                updateCartItem(item .getId(), item.getProduct().getId(),item.getCart().getId(), quantity);
            }
            else {
                try {
                    CartItem cartItem = new CartItem(quantity, productDAO.get(productId), cartDAO.get(cartId));
                    cartItemDAO.insert(cartItem);

                    message = "A new cartItem was created successfully!";
                    messageType = "success";
                } catch (Exception e) {
                    e.printStackTrace();
                    message = "An error occurred when creating a new cartItem! Please try again.";
                    messageType = "danger";
                }
            }

        } else {
            message = String.format("A cartItem with productid %s  already exists! Please choose another user", productId);
            messageType = "danger";
        }

        serviceResult.setMessage(message);
        serviceResult.setMessageType(messageType);
    }

    public void deleteCartItem(int id) {
        CartItem cartItem = cartItemDAO.get(id);
        String message, messageType;
        if (cartItem != null) {
                try {
                    cartItemDAO.delete(id);
                    message = "CartItem was deleted successfully!";
                    messageType = "primary";
                } catch (Exception e) {
                    e.printStackTrace();
                    message = "An error occurred when creating a new cartItem! Please try again.";
                    messageType = "danger";
                }

        } else {
            message = String.format("CartItem with id %s does not exist", id);
            messageType = "danger";
        }

        serviceResult.setMessage(message);
        serviceResult.setMessageType(messageType);
    }

    public void updateCartItem(int id, int productid , int cartid , int quantity ) {
        CartItem cartItem = cartItemDAO.get(id);
        String message = "", messageType = "";
        if (cartItem != null) {
            try {
                cartItem.setQuantity(quantity);
                cartItemDAO.update(cartItem);
                message = "CartItem's info was changed successfully!";
                messageType = "success";
            } catch (Exception e) {
                e.printStackTrace();
                message = "An error occurred when creating a new cartItem! Please try again.";
                messageType = "danger";
            }

        } else {
            message = String.format("CartItem with id %s does not exist", id);
            messageType = "danger";
        }

        serviceResult.setMessage(message);
        serviceResult.setMessageType(messageType);
    }

    public CartItem getCartItem(int id) {
        return cartItemDAO.get(id);
    }
}
