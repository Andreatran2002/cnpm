package com.onlinestorewepr.controller.admin.product;

import com.onlinestorewepr.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/delete-product")
public class DeleteProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        ProductService productService = new ProductService();

        if (id != 0) {
            productService.delete(id);
        }

        req.setAttribute("message", productService.getServiceResult().getMessage());
        req.setAttribute("messageType", productService.getServiceResult().getMessageType());
        req.setAttribute("title", "Delete Information");
        req.setAttribute("action", "/admin/products");

        req.getRequestDispatcher("/admin/information.jsp").forward(req, resp);
    }
}
