package com.onlinestorewepr.service;

import com.onlinestorewepr.dao.OrderDAO;
import com.onlinestorewepr.dao.UserDAO;
import com.onlinestorewepr.entity.Order;
import java.util.Date;
import java.util.List;

public class OrderService {
    private OrderDAO orderDAO;
    private UserDAO userDAO;
    private ServiceResult serviceResult;

    public ServiceResult getServiceResult() {
        return serviceResult;
    }

    public void setServiceResult(ServiceResult serviceResult) {
        this.serviceResult = serviceResult;
    }

    public OrderService() {
        orderDAO = new OrderDAO();
        userDAO = new UserDAO();
        serviceResult= new ServiceResult();
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

    public void updateOrder(int id,String name, String phone, String address, int total, String note, String payment, String status) {
        Order order = orderDAO.get(id);
        String message = "", messageType = "";
        if (order != null) {
            try {
                order.setName(name);
                order.setPhone(phone);
                order.setAddress(address);
                order.setTotal(total);
                order.setNote(note);
                order.setPayment(payment);
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
            message = String.format("Order with name %s does not exist", name);
            messageType = "danger";
        }

        serviceResult.setMessage(message);
        serviceResult.setMessageType(messageType);
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
