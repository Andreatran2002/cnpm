package com.onlinestorewepr.controller.admin.order;

import com.onlinestorewepr.entity.Category;
import com.onlinestorewepr.entity.Order;
import com.onlinestorewepr.entity.Order;
import com.onlinestorewepr.service.CategoryService;
import com.onlinestorewepr.service.OrderService;
import com.onlinestorewepr.service.OrderService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
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
    Order order = orderService.getOrder(id);
    DiskFileItemFactory diskFileItemFactory = new
            DiskFileItemFactory();
    ServletFileUpload servletFileUpload = new
            ServletFileUpload(diskFileItemFactory);
    servletFileUpload.setHeaderEncoding("UTF-8");
    String phone ="", address="", status ="";
    try {
      resp.setContentType("text/html");
      resp.setCharacterEncoding("UTF-8");
      req.setCharacterEncoding("UTF-8");
      List<FileItem> items = servletFileUpload.parseRequest(req);
      for (FileItem item : items) {
        if (item.getFieldName().equals("order-phone")) {
          phone =item.getString("UTF-8");
        }
        if (item.getFieldName().equals("order-address")) {
          address = item.getString("UTF-8");
        }
        if (item.getFieldName().equals("order-status")) {
          status = item.getString("UTF-8");
        }
      }
      orderService.updateOrder(order.getId(), phone, address, status );
      System.out.println(phone);
      resp.sendRedirect(req.getContextPath() + "/admin/orders");
    } catch (FileUploadException e) {
      e.printStackTrace();
    } catch (Exception e) {e.printStackTrace();}

  }

}
