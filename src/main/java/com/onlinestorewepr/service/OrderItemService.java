//package com.onlinestorewepr.service;
//
//import com.onlinestorewepr.dao.OrderDAO;
//import com.onlinestorewepr.dao.OrderItemDAO;
//import com.onlinestorewepr.dao.ProductDAO;
//import com.onlinestorewepr.dao.UserDAO;
//import com.onlinestorewepr.entity.OrderItem;
//
//public class OrderItemService {
//    private OrderItemDAO cartItemDAO;
//    private OrderDAO cartDAO;
//    private ProductDAO productDAO;
//    private UserDAO userDAO;
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
//    public OrderItemService() {
//        userDAO = new UserDAO();
//        cartDAO = new OrderDAO();
//        productDAO = new ProductDAO();
//        cartItemDAO = new OrderItemDAO();
//        serviceResult= new ServiceResult();
//    }
//
//    public void createOrderItem(int cartId, int productId, int quantity  ) {
//        String message ="", messageType  ="";
//        //check cartid and productid
//        if (cartDAO.get(cartId) != null && productDAO.get(productId) != null) {
//            if (cartItemDAO.(cartId, productId) != null){
//                OrderItem item = cartItemDAO.findByProductId(cartId, productId) ;
//                updateOrderItem(item .getId(), item.getProduct().getId(),item.getOrder().getId(), quantity);
//            }
//            else {
//                try {
//                    OrderItem cartItem = new OrderItem(quantity, productDAO.get(productId), cartDAO.get(cartId));
//                    cartItemDAO.insert(cartItem);
//
//                    message = "A new cartItem was created successfully!";
//                    messageType = "success";
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    message = "An error occurred when creating a new cartItem! Please try again.";
//                    messageType = "danger";
//                }
//            }
//
//        } else {
//            message = String.format("A cartItem with productid %s  already exists! Please choose another user", productId);
//            messageType = "danger";
//        }
//
//        serviceResult.setMessage(message);
//        serviceResult.setMessageType(messageType);
//    }
//
//    public void deleteOrderItem(int id) {
//        OrderItem cartItem = cartItemDAO.get(id);
//        String message, messageType;
//        if (cartItem != null) {
//            try {
//                cartItemDAO.delete(id);
//                message = "OrderItem was deleted successfully!";
//                messageType = "primary";
//            } catch (Exception e) {
//                e.printStackTrace();
//                message = "An error occurred when creating a new cartItem! Please try again.";
//                messageType = "danger";
//            }
//
//        } else {
//            message = String.format("OrderItem with id %s does not exist", id);
//            messageType = "danger";
//        }
//
//        serviceResult.setMessage(message);
//        serviceResult.setMessageType(messageType);
//    }
//
//    public void updateOrderItem(int id, int productid , int cartid , int quantity ) {
//        OrderItem cartItem = cartItemDAO.get(id);
//        String message = "", messageType = "";
//        if (cartItem != null) {
//            try {
//                cartItem.setQuantity(quantity);
//                cartItemDAO.update(cartItem);
//                message = "OrderItem's info was changed successfully!";
//                messageType = "success";
//            } catch (Exception e) {
//                e.printStackTrace();
//                message = "An error occurred when creating a new cartItem! Please try again.";
//                messageType = "danger";
//            }
//
//        } else {
//            message = String.format("OrderItem with id %s does not exist", id);
//            messageType = "danger";
//        }
//
//        serviceResult.setMessage(message);
//        serviceResult.setMessageType(messageType);
//    }
//
//    public OrderItem getOrderItem(int id) {
//        return cartItemDAO.get(id);
//    }
//}
