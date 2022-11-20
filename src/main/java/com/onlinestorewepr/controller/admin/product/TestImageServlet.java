package com.onlinestorewepr.controller.admin.product;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@MultipartConfig
@WebServlet("/test")
public class TestImageServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/admin/test.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Part part = req.getPart("image");
    String fileName =  Paths.get(part.getSubmittedFileName()).getFileName().toString();

    String servletContext = req.getServletContext().toString();
    String servletPath = req.getServletPath().toString();

    System.out.println("Context: "+servletContext);
    System.out.println("Path: "+servletPath);

//
//
//    Path path = Paths.get(imagePath);
//    if (!Files.exists(path))
//      Files.createDirectories(path);
//    String imageUrl = imagePath + "\\" + fileName;
    // ------
  }
}
