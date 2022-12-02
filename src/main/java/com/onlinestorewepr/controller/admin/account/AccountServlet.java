package com.onlinestorewepr.controller.admin.account;

import com.onlinestorewepr.entity.Category;
import com.onlinestorewepr.entity.User;
import com.onlinestorewepr.service.CategoryService;
import com.onlinestorewepr.service.ProductService;
import com.onlinestorewepr.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/accounts")
public class AccountServlet extends HttpServlet {


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    UserService userService = new UserService(req,resp);
    userService.ListUser();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  }
}
