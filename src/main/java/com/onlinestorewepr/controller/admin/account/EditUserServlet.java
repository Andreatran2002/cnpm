//package com.onlinestorewepr.controller.admin.account;
//
//import com.onlinestorewepr.entity.User;
//import com.onlinestorewepr.service.UserService;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet("/admin/edit-account")
//public class EditUserServlet extends HttpServlet {
//  @Override
//  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    int id = Integer.parseInt(req.getParameter("id"));
//    if (id != 0) {
//      UserService userService = new UserService();
//      User user = userService.getUser(id);
//      req.setAttribute("user", user);
//      req.setAttribute("action", "edit");
//    }
//    req.getRequestDispatcher("/admin/update-user.jsp").forward(req, resp);
//  }
//
//  @Override
//  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    int id = Integer.parseInt(req.getParameter("id"));
//    String name = req.getParameter("name").trim();
//    String description = req.getParameter("description").trim();
//
//    UserService userService = new UserService();
//
////    if (id != 0 && !name.isEmpty() && !description.isEmpty()) {
////      userService.updateUser(id, name, description);
////    }
////    else {
////      userService.getServiceResult().setMessage("Name and description cannot be empty");
////      userService.getServiceResult().setMessageType("danger");
////    }
////
////    User user = userService.getUser(id);
////
////    req.setAttribute("user", user);
////    req.setAttribute("action", "update");
////    req.setAttribute("message", userService.getServiceResult().getMessage());
////    req.setAttribute("messageType", userService.getServiceResult().getMessageType());
////
////    req.getRequestDispatcher("/admin/update-user.jsp").forward(req, resp);
//  }
//}
