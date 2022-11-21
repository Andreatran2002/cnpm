package com.onlinestorewepr.controller.admin.category;

import com.onlinestorewepr.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/add-category")
public class AddCategoryServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("action", "add");
    req.getRequestDispatcher("/admin/update-category.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String name = req.getParameter("name").trim();
    String description = req.getParameter("description").trim();

    CategoryService categoryService = new CategoryService();

    if (!name.isEmpty() && !description.isEmpty()) {
      categoryService.createCategory(name, description);
    } else {
      categoryService.getServiceResult().setMessage("Name and description cannot be empty!");
      categoryService.getServiceResult().setMessageType("danger");
    }

    req.setAttribute("action", "add");
    req.setAttribute("message", categoryService.getServiceResult().getMessage());
    req.setAttribute("messageType", categoryService.getServiceResult().getMessageType());
    req.getRequestDispatcher("/admin/update-category.jsp").forward(req, resp);
  }
}
