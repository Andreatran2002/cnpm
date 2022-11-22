package com.onlinestorewepr.controller.web.product;

import com.onlinestorewepr.entity.Product;
import com.onlinestorewepr.service.CategoryService;
import com.onlinestorewepr.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/search-product")
public class SearchProductServlet extends HttpServlet {
//  ProductService productService  =new ProductService();
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    String search = req.getParameter("search");
    if (search == null)search = "";

    resp.setCharacterEncoding("UTF-8");
//    List<Product> products = productService.searchByName(search);


//    PrintWriter out = resp.getWriter();
//    for (Product product : products) {
//      out.println("       <div class=\"col-lg-4 col-md-6 col-sm-6\">\n" +
//              "                     <div class=\"product__item "+(product.getDiscount()>0?"sale":"") +"\">\n" +
//              "                        <div class=\"product__item__pic set-bg\" data-setbg=\"./web/assets/img/product/product-2.jpg\">\n" +
//              "                           <ul class=\"product__hover\">\n" +
//              "                              <li><a href=\"#\"><img src=\"./web/assets/img/icon/heart.png\" alt=\"\"></a></li>\n" +
//              "                              <li><a href=\"#\"><img src=\"./web/assets/img/icon/compare.png\" alt=\"\"> <span>Compare</span></a>\n" +
//              "                              </li>\n" +
//              "                              <li><a href=\"#\"><img src=\"./web/assets/img/icon/search.png\" alt=\"\"></a></li>\n" +
//              "                           </ul>\n" +
//              "                        </div>\n" +
//              "                        <div class=\"product__item__text\">\n" +
//              "                           <h6>"+product.getName()+"</h6>\n" +
//              "                           <a href=\"#\" class=\"add-cart\">+ Add To Cart</a>\n" +
//              "                           <div class=\"rating\">\n" +
//              "                              <i class=\"fa fa-star-o\"></i>\n" +
//              "                              <i class=\"fa fa-star-o\"></i>\n" +
//              "                              <i class=\"fa fa-star-o\"></i>\n" +
//              "                              <i class=\"fa fa-star-o\"></i>\n" +
//              "                              <i class=\"fa fa-star-o\"></i>\n" +
//              "                           </div>\n" +
//              "                           <h5>"+product.getPrice()+"</h5>\n" +
//              "                           <div class=\"product__color__select\">\n" +
//              "\n" +
//              "                              <label class=\"active "+product.getColor()+"\" for=\"pc-5\">\n" +
//              "                                 <input type=\"radio\" id=\"pc-5\">\n" +
//              "                              </label>\n" +
//              "\n" +
//              "                           </div>\n" +
//              "                        </div>\n" +
//              "                     </div>\n" +
//              "                  </div>");
//    }

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

  }
}
