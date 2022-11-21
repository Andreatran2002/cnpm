package com.onlinestorewepr.controller.admin.order;

import com.onlinestorewepr.entity.Category;
import com.onlinestorewepr.entity.Order;
import com.onlinestorewepr.entity.Order;
import com.onlinestorewepr.service.CategoryService;
import com.onlinestorewepr.service.OrderService;
import com.onlinestorewepr.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/edit-order")
public class EditOrderServlet extends HttpServlet {
  OrderService orderService = new OrderService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int id = Integer.parseInt(req.getParameter("id"));
    if (id != 0) {
      Order order = orderService.getOrder(id);
      req.setAttribute("order", order);
      req.setAttribute("action", "edit");
    }

    req.getRequestDispatcher("/admin/update-order.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int id = Integer.parseInt(req.getParameter("id"));
    String phone = req.getParameter("phone").trim();
    String address = req.getParameter("address").trim();
    String status = req.getParameter("status").trim();


    if (id != 0 && !phone.isEmpty() && !address.isEmpty() && !status.isEmpty() ) {
      orderService.updateOrder(id, phone, address,status);
    }
    else {
      orderService.getServiceResult().setMessage("Name and description cannot be empty");
      orderService.getServiceResult().setMessageType("danger");
    }

    Order order = orderService.getOrder(id);

    req.setAttribute("order", order);
    req.setAttribute("action", "update");
    req.setAttribute("message", orderService.getServiceResult().getMessage());
    req.setAttribute("messageType", orderService.getServiceResult().getMessageType());

    req.getRequestDispatcher("/admin/update-order.jsp").forward(req, resp);
  }

}
