package com.onlinestorewepr.controller.web.order;

import com.onlinestorewepr.util.EmailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TestEmailServlet", value = {"/test-email"})
public class TestEmailServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      EmailUtil.sendEmail("20110160@student.hcmute.edu.vn", "Test email", "Email servlet", "tranvanquangforever@gmail.com", "fmhwuvwkglyggykn");
      System.out.println("Done");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
