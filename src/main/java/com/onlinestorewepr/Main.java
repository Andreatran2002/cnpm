package com.onlinestorewepr;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.onlinestorewepr.dao.*;
import com.onlinestorewepr.entity.Cart;
import com.onlinestorewepr.entity.CartItem;
import com.onlinestorewepr.entity.Category;
import com.onlinestorewepr.entity.User;
import com.onlinestorewepr.service.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
  private static void insertData() {
    User user = new UserDAO().get("quangtv");
    Cart cart = user.getCart();
    System.out.println(cart);
    CartItem c1 = new CartItem(2, new ProductDAO().get(3), cart);
    CartItem c2 = new CartItem(1, new ProductDAO().get(3), cart);
    CartItemDAO cartItemDAO = new CartItemDAO();
    cartItemDAO.insert(c1);
    cartItemDAO.insert(c2);
  }
  public static void main(String[] args) {
    User user = new UserDAO().get("quangtv");
    System.out.println("Username: " + user.getName());
    List<CartItem> cartItems = user.getCart().getCartItems();

    if (cartItems.size() > 0) {
      for (int i = 0; i < cartItems.size(); i++) {
        System.out.println("Cart item line " + i);
        System.out.println("Product name: " + cartItems.get(i).getProduct().getName());
        System.out.println("Product quantity: " + cartItems.get(i).getQuantity());
      }
    }
    else {
      System.out.println("Cart items is empty");
    }

//    insertData();

  }
}
