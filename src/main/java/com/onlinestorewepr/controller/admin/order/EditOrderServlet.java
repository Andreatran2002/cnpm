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

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    OrderService orderService = new OrderService(req,resp);
    orderService.ShowEditOrderServlet();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    OrderService orderService = new OrderService(req,resp);
    orderService.UpdateOrder();
  }

}
