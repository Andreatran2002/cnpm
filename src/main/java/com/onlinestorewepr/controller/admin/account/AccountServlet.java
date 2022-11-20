package com.onlinestorewepr.controller.admin.account;

import com.onlinestorewepr.entity.Category;
import com.onlinestorewepr.entity.User;
import com.onlinestorewepr.service.AccountService;
import com.onlinestorewepr.service.CategoryService;
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
  AccountService accountService = new AccountService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    List<User> accounts = accountService.getAll();
    req.setAttribute("accounts", accounts);
    req.getRequestDispatcher("/admin/accounts.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  }
}
