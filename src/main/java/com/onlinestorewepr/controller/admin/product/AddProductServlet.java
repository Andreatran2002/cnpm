package com.onlinestorewepr.controller.admin.product;

import com.onlinestorewepr.entity.Category;
import com.onlinestorewepr.service.CategoryService;
import com.onlinestorewepr.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@MultipartConfig
@WebServlet("/admin/add-product")
public class AddProductServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    CategoryService categoryService = new CategoryService();
    List<Category> categories = categoryService.getAllCategories();
    req.setAttribute("action", "add");
    req.setAttribute("categories", categories);
    req.getRequestDispatcher("/admin/update-product.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int categoryId = Integer.parseInt(req.getParameter("category-id"));
    String name = req.getParameter("name").trim();
    Part imagePart = req.getPart("image");
    String imagePath = req.getServletContext().getRealPath("\\image");
    String description = req.getParameter("description").trim();
    int price = Integer.parseInt(req.getParameter("price"));
    int discount = Integer.parseInt(req.getParameter("discount"));
    int quantity = Integer.parseInt(req.getParameter("quantity"));
    String size = req.getParameter("size").trim();
    String color = req.getParameter("color").trim();
    String brand = req.getParameter("brand").trim();

    ProductService productService = new ProductService();

    System.out.println(categoryId);
    System.out.println(name);
    System.out.println(imagePart);
    System.out.println(imagePath);
    System.out.println(description);
    System.out.println(price);
    System.out.println(discount);
    System.out.println(quantity);
    System.out.println(size);
    System.out.println(color);
    System.out.println(brand);

    boolean isValid = (categoryId != 0 &&
        !name.isEmpty() &&
        imagePart != null &&
        !description.isEmpty() &&
        price > 0 &&
        discount >= 0 &&
        discount <= 100 &&
        quantity >= 0 &&
        !size.isEmpty() &&
        !color.isEmpty() &&
        !brand.isEmpty());

    if (isValid) {
      productService.createProduct(categoryId, name, imagePart, imagePath, description, price, discount, quantity, size, color, brand);
    } else {
      productService.getServiceResult().setMessage("All fields cannot be empty!");
      productService.getServiceResult().setMessageType("danger");
    }

    req.setAttribute("action", "add");
    req.setAttribute("message", productService.getServiceResult().getMessage());
    req.setAttribute("messageType", productService.getServiceResult().getMessageType());
    req.getRequestDispatcher("/admin/update-product.jsp").forward(req, resp);
  }
}
