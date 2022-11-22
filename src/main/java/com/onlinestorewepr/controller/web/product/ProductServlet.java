package com.onlinestorewepr.controller.web.product;

import com.onlinestorewepr.entity.Category;
import com.onlinestorewepr.entity.Product;
import com.onlinestorewepr.service.CategoryService;
import com.onlinestorewepr.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
//    ProductService productService = new ProductService();
//    CategoryService categoryService = new CategoryService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String key = req.getParameter("key");
//        String cateIdReq = req.getParameter("cateid");
//
//        List<Product> products = productService.getProductPaging(0, 9);
//
//
//        if (cateIdReq!= null){
//            Integer cateId = Integer.parseInt(cateIdReq);
//             products = productService.getByCategory(cateId);
//        }
//
//        List<Category> categories = categoryService.getAllCategories();
//        req.setAttribute("products", products);
//        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/web/shop.jsp").forward(req, resp);
    }
}
