package com.onlinestorewepr.service;

import com.onlinestorewepr.dao.CartDAO;
import com.onlinestorewepr.dao.CartDAO;
import com.onlinestorewepr.dao.CategoryDAO;
import com.onlinestorewepr.dao.UserDAO;
import com.onlinestorewepr.entity.Cart;
import com.onlinestorewepr.entity.User;
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

  public MessageUtil getMessage() {
    return message;
  }

  public void setMessage(MessageUtil message) {
    this.message = message;
  }

  public CartDAO getCartDAO() {
    return cartDAO;
  }

  public void setCartDAO(CartDAO cartDAO) {
    this.cartDAO = cartDAO;
  }

  public CartService(HttpServletRequest req, HttpServletResponse resp) {
    this.req = req;
    this.resp = resp;
    this.cartDAO = new CartDAO();
    this.message = new MessageUtil();
  }

  public CartService() {
    userDAO = new UserDAO();
    cartDAO = new CartDAO();
    serviceResult = new ServiceResult();
  }

  public void viewCart() throws ServletException, IOException {
    // Get user login from session
    // Fake login info
    String username = "quangtv";

    User user = new UserDAO().get(username);
    if (user != null) {
      req.setAttribute("cart", user.getCart());
      req.setAttribute("cartItems", user.getCart().getCartItems());
      req.getRequestDispatcher("/web/shopping-cart.jsp").forward(req, resp);
      return;
    }
    resp.sendRedirect(req.getContextPath() + "/login");
  }
}

//  public void createCart(Integer userId) {
//    String message, messageType;

    // Check if a cart with the same name already exists in DB
//    if (cartDAO.findByUserId(userId) == null) {
//      try {
//        Cart cart = new Cart(0, userDAO.getByID(userId));
//        cartDAO.insert(cart);
//
//        message = "A new cart was created successfully!";
//        messageType = "success";
//      } catch (Exception e) {
//        e.printStackTrace();
//        message = "An error occurred when creating a new cart! Please try again.";
//        messageType = "danger";
//      }
//    } else {
//      message = String.format("A cart with userid %s already exists! Please choose another user", userId);
//      messageType = "danger";
//    }
//
//    serviceResult.setMessage(message);
//    serviceResult.setMessageType(messageType);
//  }

//  public CartService(HttpServletRequest req, HttpServletResponse resp) {
//      userDAO = new UserDAO();
//      cartDAO = new CartDAO();
//      this.req = req;
//      this.resp = resp;
//      serviceResult= new ServiceResult();
//  }

    // public void CreateCart() {

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
  //   }

  //   serviceResult.setMessage(message);
  //   serviceResult.setMessageType(messageType);
  // }

//  public void updateCart(int id, int total) {
//    Cart cart = cartDAO.get(id);
//    String message = "", messageType = "";
//    if (cart != null) {
//      try {
//        cart.setTotal(total);
//        cartDAO.update(cart);
//
//        message = "Cart's info was changed successfully!";
//        messageType = "success";
//      } catch (Exception e) {
//        e.printStackTrace();
//        message = "An error occurred when creating a new cart! Please try again.";
//        messageType = "danger";
//      }
//
//    } else {
//      message = String.format("Cart with id %s does not exist", id);
//      messageType = "danger";
//    }
//
//    serviceResult.setMessage(message);
//    serviceResult.setMessageType(messageType);
//  }
//
//  public Cart getCart(int id) {
//    return cartDAO.get(id);
//  }
//  public Cart getCartByUser(int userid){
//    return cartDAO.findByUserId(userid);
//  }
//}
