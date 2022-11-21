package com.onlinestorewepr.controller.admin.category;

import com.onlinestorewepr.entity.Category;
import com.onlinestorewepr.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/edit-category")
public class EditCategoryServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int id = Integer.parseInt(req.getParameter("id"));
    if (id != 0) {
      CategoryService categoryService = new CategoryService();
      Category category = categoryService.getCategory(id);
      req.setAttribute("category", category);
      req.setAttribute("action", "edit");
    }
    req.getRequestDispatcher("/admin/update-category.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int id = Integer.parseInt(req.getParameter("id"));
    String name = req.getParameter("name").trim();
    String description = req.getParameter("description").trim();

    CategoryService categoryService = new CategoryService();

    if (id != 0 && !name.isEmpty() && !description.isEmpty()) {
      categoryService.updateCategory(id, name, description);
    }
    else {
      categoryService.getServiceResult().setMessage("Name and description cannot be empty");
      categoryService.getServiceResult().setMessageType("danger");
    }

    Category category = categoryService.getCategory(id);

    req.setAttribute("category", category);
    req.setAttribute("action", "update");
    req.setAttribute("message", categoryService.getServiceResult().getMessage());
    req.setAttribute("messageType", categoryService.getServiceResult().getMessageType());

    req.getRequestDispatcher("/admin/update-category.jsp").forward(req, resp);
  }
}
