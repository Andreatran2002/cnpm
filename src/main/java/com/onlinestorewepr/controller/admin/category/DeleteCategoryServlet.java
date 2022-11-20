package com.onlinestorewepr.controller.admin.category;

import com.onlinestorewepr.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/admin/delete-category")
public class DeleteCategoryServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int id = Integer.parseInt(req.getParameter("id"));

    CategoryService categoryService = new CategoryService();

    if (id != 0) {
      categoryService.deleteCategory(id);
    }

    req.setAttribute("message", categoryService.getServiceResult().getMessage());
    req.setAttribute("messageType", categoryService.getServiceResult().getMessageType());
    req.setAttribute("title", "Delete Information");
    req.setAttribute("action", "/admin/categories");

    req.getRequestDispatcher("/admin/information.jsp").forward(req, resp);
  }
}
