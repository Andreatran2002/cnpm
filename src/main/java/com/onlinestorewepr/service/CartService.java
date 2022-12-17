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
import javax.servlet.http.HttpSession;
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
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("userLogged");
    if (user != null) {
      Cart cart = new CartDAO().get(user.getCart().getId());
      req.setAttribute("cart", cart);
      req.setAttribute("cartItems", cart.getCartItems());
      req.getRequestDispatcher("/web/shopping-cart.jsp").forward(req, resp);
    } else {
      resp.sendRedirect(req.getContextPath() + "/login");
    }
  }
}
