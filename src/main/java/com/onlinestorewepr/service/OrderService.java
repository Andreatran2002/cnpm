//package com.onlinestorewepr.service;
//
//import com.onlinestorewepr.dao.OrderDAO;
//import com.onlinestorewepr.entity.Order;
//import com.onlinestorewepr.entity.Product;
//import com.onlinestorewepr.entity.User;
//
//import javax.servlet.http.Part;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Date;
//import java.util.List;
//
//public class OrderService {
//    private OrderDAO orderDAO;
//    private ServiceResult serviceResult;
//
//    public ServiceResult getServiceResult() {
//        return serviceResult;
//    }
//
//    public void setServiceResult(ServiceResult serviceResult) {
//        this.serviceResult = serviceResult;
//    }
//
//    public OrderService() {
//        orderDAO = new OrderDAO();
//        serviceResult= new ServiceResult();
//    }
//
//    public void createOrder(String name, String phone, String address, Date created, int total, String note, String payment, String status, User user) {
//        String message, messageType;
//
//        // Check if a order with the same name already exists in DB
//        if (orderDAO.findByName(name) == null) {
//            try {
//                Order order = new Order(name, description);
//                orderDAO.insert(order);
//
//                message = "A new order was created successfully!";
//                messageType = "success";
//            } catch (Exception e) {
//                e.printStackTrace();
//                message = "An error occurred when creating a new order! Please try again.";
//                messageType = "danger";
//            }
//        } else {
//            message = String.format("A order with name %s already exists! Please choose another name", name);
//            messageType = "danger";
//        }
//
//        serviceResult.setMessage(message);
//        serviceResult.setMessageType(messageType);
//    }
//
//    public void deleteOrder(int id) {
//        Order order = orderDAO.get(id);
//        String message, messageType;
//        if (order != null) {
//            if (order.getProducts() == null || order.getProducts().isEmpty()) {
//                try {
//                    orderDAO.delete(id);
//                    message = "Order was deleted successfully!";
//                    messageType = "primary";
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    message = "An error occurred when creating a new order! Please try again.";
//                    messageType = "danger";
//                }
//            } else {
//                message = String.format("Could not delete the order %s because it currently contains some products.", order.getName());
//                messageType = "danger";
//            }
//        } else {
//            message = String.format("Order with id %s does not exist", id);
//            messageType = "danger";
//        }
//
//        serviceResult.setMessage(message);
//        serviceResult.setMessageType(messageType);
//    }
//
//    public void updateOrder(int id, String name, String description) {
//        Order order = orderDAO.get(id);
//        String message = "", messageType = "";
//        if (order != null) {
//            try {
//                order.setName(name);
//                order.setDescription(description);
//                orderDAO.update(order);
//
//                message = "Order's info was changed successfully!";
//                messageType = "success";
//            } catch (Exception e) {
//                e.printStackTrace();
//                message = "An error occurred when creating a new order! Please try again.";
//                messageType = "danger";
//            }
//
//        } else {
//            message = String.format("Order with name %s does not exist", name);
//            messageType = "danger";
//        }
//
//        serviceResult.setMessage(message);
//        serviceResult.setMessageType(messageType);
//    }
//
//    public Order getOrder(int id) {
//        return orderDAO.get(id);
//    }
//
//    public List<Order> getAllCategories() {
//        List<Order> categories = null;
//
//        categories = orderDAO.getAll();
//        if (categories != null) {
//            categories.sort((o1, o2) -> {
//                int compareValue = o1.getName().compareTo(o2.getName());
//                return (Integer.compare(compareValue, 0));
//            });
//        }
//
//        return categories;
//    }
//}
