package com.onlinestorewepr.controller.admin.account;

import com.onlinestorewepr.service.AccountService;
import com.onlinestorewepr.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/delete-account")
public class DeleteUserServlet extends HttpServlet {
    AccountService userService = new AccountService();

    @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String idRequest = req.getParameter("id");
     if(idRequest != null){
         int id = Integer.parseInt(idRequest);
         userService.remove(id);

     }


    req.setAttribute("title", "Delete Information");
    req.setAttribute("action", "/admin/accounts");

    req.getRequestDispatcher("/admin/information.jsp").forward(req, resp);
  }
}
