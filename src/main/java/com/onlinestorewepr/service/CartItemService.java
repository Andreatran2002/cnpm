package com.onlinestorewepr.service;

import com.onlinestorewepr.dao.CartDAO;
import com.onlinestorewepr.dao.CartItemDAO;
import com.onlinestorewepr.dao.ProductDAO;
import com.onlinestorewepr.dao.UserDAO;
import com.onlinestorewepr.entity.Cart;
import com.onlinestorewepr.entity.CartItem;
import com.onlinestorewepr.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartItemService {
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private CartItemDAO cartItemDAO;
    private CartDAO cartDAO;
    private ProductDAO productDAO;
    private UserDAO userDAO;
    private ServiceResult serviceResult;

    public CartItemService(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
        userDAO = new UserDAO();
        cartDAO = new CartDAO();
        productDAO = new ProductDAO();
        cartItemDAO = new CartItemDAO();
        serviceResult= new ServiceResult();
    }

    public ServiceResult getServiceResult() {
        return serviceResult;
    }

    public void setServiceResult(ServiceResult serviceResult) {
        this.serviceResult = serviceResult;
    }



    public void createCartItem(String username ) {
        try{
            //        System.out.println();
            String message = "",messageType ="";
            String productId = req.getParameter("productid").trim();
            String quantity = req.getParameter("quantity").trim();
            if (username != null && productId != null && quantity != null){
                Cart cart = cartDAO.findByUser(username) ;
                System.out.println(cart.getTotal());

                Product product = productDAO.get(Integer.parseInt(productId));
                System.out.println(product.getName());

                if (cart != null && product != null) {
                    CartItem item ;
                    if (cartItemDAO.findByProductId(cart.getId(), Integer.parseInt(productId) ) != null){
                         item = cartItemDAO.findByProductId(cart.getId(), Integer.parseInt(productId) );

                        updateCartItem(item.getId());
                    }
                    else {
                         item = new CartItem(Integer.parseInt(quantity), product, cart);
                        cartItemDAO.insert(item);
                    }
                    cart.setTotal(cart.getTotal()+Double.parseDouble(quantity)*item.getProduct().getPrice());
                    cartDAO.update(cart);


                } else {
                    message = String.format("User or product is not valid");
                    messageType = "danger";
                }
            }
            else{
                System.out.println("Khong co dux liu");
            }
            req.setAttribute("message",message);
            req.setAttribute("messageType",messageType);

            System.out.println(message);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void deleteCartItem() throws IOException {
        String message, messageType;

        String id = req.getParameter("id").trim();
        if (id != null){
            CartItem cartItem = cartItemDAO.get(Integer.parseInt(id));
            if (cartItem != null) {
                try {
                    Cart cart = cartItem.getCart();
                    cart.setTotal(cart.getTotal()- cartItem.getQuantity()*cartItem.getProduct().getPrice());
                    cartDAO.update(cart);
                    cartItemDAO.delete(Integer.parseInt(id));

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


        }
        else {
            message = "Missing id params.";
            messageType = "danger";
        }
        serviceResult.setMessage(message);
        serviceResult.setMessageType(messageType);
        resp.sendRedirect(req.getContextPath() + "/cart");


    }
    public void deleteCartItem(int id) throws IOException {
        String message, messageType;

        CartItem cartItem = cartItemDAO.get(id);
        if (cartItem != null) {
            try {
                Cart cart = cartItem.getCart();
                cart.setTotal(cart.getTotal()- cartItem.getQuantity()*cartItem.getProduct().getPrice());
                cartDAO.update(cart);
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
        resp.sendRedirect(req.getContextPath() + "/cart");


    }

    public void updateCartItem(int id  ) {
        String quantity = req.getParameter("quantity").trim();
        String message = "", messageType = "";

        if ( quantity == null){
            message = "An error occurred when updating a cartItem! Please try again.";
            messageType = "danger";
            return ;
        }
        CartItem cartItem = cartItemDAO.get(id);
        if (cartItem != null) {
            try {
                cartItem.setQuantity(Integer.parseInt(quantity));
                Cart cart = cartItem.getCart();
                cart.setTotal(cart.getTotal()- (cartItem.getQuantity()-Integer.parseInt(quantity))*cartItem.getProduct().getPrice());
                cartDAO.update(cart);
                cartItemDAO.update(cartItem);
                if ( cartItem.getQuantity() == 0){
                    deleteCartItem(id);
                }
                message = "CartItem's info was changed successfully!";
                messageType = "success";
            } catch (Exception e) {
                e.printStackTrace();
                message = "An error occurred when update a cartItem! Please try again.";
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
