package com.onlinestorewepr;

import com.onlinestorewepr.dao.UserDAO;
import com.onlinestorewepr.entity.User;

public class Main {
  public static void main(String[] args) {
    UserDAO userDAO = new UserDAO();
    User user = new User();

    user.setName("Dĩ");
    user.setAddress("Binh Dinh");
    user.setAdmin(false);
    user.setGender("Nam");
    user.setUsername("dihuynh123");
    user.setPassword("123456");
    user.setPhone("03726396211");

    userDAO.insert(user);
  }
}
