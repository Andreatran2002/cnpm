package com.onlinestorewepr.controller.admin.category;

import com.onlinestorewepr.entity.Category;
import com.onlinestorewepr.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/categories")
public class CategoryServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    CategoryService categoryService = new CategoryService();
    List<Category> categories = categoryService.getAllCategories();

    req.setAttribute("categories", categories);
    req.getRequestDispatcher("/admin/categories.jsp").forward(req, resp);
  }

}
