package com.onlinestorewepr.service;

import com.onlinestorewepr.dao.CategoryDAO;
import com.onlinestorewepr.dao.OrderDAO;
import com.onlinestorewepr.dao.UserDAO;
import com.onlinestorewepr.entity.Category;
import com.onlinestorewepr.entity.Order;
import com.onlinestorewepr.util.MessageUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class OrderService {
    private OrderDAO orderDAO;
    private UserDAO userDAO;
    private ServiceResult serviceResult;
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private MessageUtil message;

    public OrderService(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
        this.userDAO = new UserDAO();
        this.orderDAO = new OrderDAO();
        this.message = new MessageUtil();
        serviceResult= new ServiceResult();
    }

    public void ListOrder() throws ServletException, IOException {
        List<Order> orders = orderDAO.getAll();
        System.out.println(orders);

        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/admin/orders.jsp").forward(req, resp);
    }
    public void ShowEditOrderServlet() throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        if (id != 0) {
            Order order = orderDAO.get(id);
            req.setAttribute("order", order);
            req.setAttribute("action", "edit");
        }

        req.getRequestDispatcher("/admin/update-order.jsp").forward(req, resp);
    }


    public ServiceResult getServiceResult() {
        return serviceResult;
    }

    public void setServiceResult(ServiceResult serviceResult) {
        this.serviceResult = serviceResult;
    }


    public void createOrder(String name, String phone, String address, int total, String note, String payment, String status, String username) {
        String message="", messageType="";
        try {
            Order order = new Order(name, phone,address, new Date() ,total,note,payment,status, userDAO.get(username));
            orderDAO.insert(order);

            message = "A new order was created successfully!";
            messageType = "success";
        } catch (Exception e) {
            e.printStackTrace();
            message = "An error occurred when creating a new order! Please try again.";
            messageType = "danger";
        }


        serviceResult.setMessage(message);
        serviceResult.setMessageType(messageType);
    }

    public void deleteOrder(int id) {
        Order order = orderDAO.get(id);
        String message, messageType;
        if (order != null) {
            if (order.getOrderItems() == null || order.getOrderItems().isEmpty()) {
                try {
                    orderDAO.delete(id);
                    message = "Order was deleted successfully!";
                    messageType = "primary";
                } catch (Exception e) {
                    e.printStackTrace();
                    message = "An error occurred when creating a new order! Please try again.";
                    messageType = "danger";
                }
            } else {
                message = String.format("Could not delete the order %s because it currently contains some products.", order.getName());
                messageType = "danger";
            }
        } else {
            message = String.format("Order with id %s does not exist", id);
            messageType = "danger";
        }

        serviceResult.setMessage(message);
        serviceResult.setMessageType(messageType);
    }

    public void UpdateOrder() {
        int id = Integer.parseInt(req.getParameter("id"));
        Order order = orderDAO.get(id);
        DiskFileItemFactory diskFileItemFactory = new
                DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new
                ServletFileUpload(diskFileItemFactory);
        servletFileUpload.setHeaderEncoding("UTF-8");
        try {
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            req.setCharacterEncoding("UTF-8");
            List<FileItem> items = servletFileUpload.parseRequest(req);
            for (FileItem item : items) {
                if (item.getFieldName().equals("order-phone")) {
                    order.setPhone(item.getString("UTF-8"));
                }
                if (item.getFieldName().equals("order-address")) {
                    order.setAddress(item.getString("UTF-8"));
                }
                if (item.getFieldName().equals("order-status")) {
                    order.setStatus(item.getString("UTF-8"));
                }
            }
            orderDAO.update(order);
            resp.sendRedirect(req.getContextPath() + "/admin/orders");
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {e.printStackTrace();}

    }
    public void updateOrder(int id, String phone, String address,  String status) {
        Order order = orderDAO.get(id);
        String message = "", messageType = "";
        if (order != null) {
            try {
                order.setPhone(phone);
                order.setAddress(address);
                order.setStatus(status);
                orderDAO.update(order);

                message = "Order's info was changed successfully!";
                messageType = "success";
            } catch (Exception e) {
                e.printStackTrace();
                message = "An error occurred when creating a new order! Please try again.";
                messageType = "danger";
            }

        } else {
            message = String.format("Order with id %s does not exist", id);
            messageType = "danger";
        }

        serviceResult.setMessage(message);
        serviceResult.setMessageType(messageType);
    }

    public Order getOrder(int id) {
        return orderDAO.get(id);
    }

    public List<Order> getAllOrders() {
        List<Order> orders = null;

        orders = orderDAO.getAll();

        return orders;
    }
}
