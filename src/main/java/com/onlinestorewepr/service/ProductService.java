package com.onlinestorewepr.service;

import com.onlinestorewepr.dao.CartDAO;
import com.onlinestorewepr.dao.CategoryDAO;
import com.onlinestorewepr.dao.ProductDAO;
import com.onlinestorewepr.entity.Cart;
import com.onlinestorewepr.entity.Category;
import com.onlinestorewepr.entity.Product;
import com.onlinestorewepr.entity.User;
import com.onlinestorewepr.util.CommonUtil;
import com.onlinestorewepr.util.MessageUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ProductService {
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;
    private CartDAO cartDAO;
    private MessageUtil message;

    public ProductService(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
        this.productDAO = new ProductDAO();
        this.categoryDAO = new CategoryDAO();
        this.cartDAO = new CartDAO();
        this.message = new MessageUtil();
    }

    public HttpServletRequest getReq() {
        return req;
    }

    public void setReq(HttpServletRequest req) {
        this.req = req;
    }

    public HttpServletResponse getResp() {
        return resp;
    }

    public void setResp(HttpServletResponse resp) {
        this.resp = resp;
    }

    public ProductDAO getProductDAO() {
        return productDAO;
    }

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    private void readData(Product product) {
        try {
            int categoryId = Integer.parseInt(req.getParameter("category-id"));
            CategoryDAO categoryDAO = new CategoryDAO();
            Category category = categoryDAO.get(categoryId);
            boolean available = req.getParameter("available") == null || (req.getParameter("available").equals("1"));
            String name = req.getParameter("name");
            String image = "temp";
            String description = req.getParameter("description");
            Double price = Double.parseDouble(req.getParameter("price"));
            Double discount = Double.parseDouble(req.getParameter("discount"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            String size = req.getParameter("size");
            String color = req.getParameter("color");
            String brand = req.getParameter("brand");

            product.setAvailable(available);
            product.setCategory(category);
            product.setName(name);
            product.setImage(image);
            product.setDescription(description);
            product.setPrice(price);
            product.setDiscount(discount);
            product.setQuantity(quantity);
            product.setSize(size);
            product.setColor(color);
            product.setBrand(brand);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void ListProducts() throws ServletException, IOException {
        List<Product> products = productDAO.getAll();
        System.out.println(products);

        if (products != null) {
            products.sort((o1, o2) -> {
                int compareValue = o1.getName().compareTo(o2.getName());
                return (Integer.compare(compareValue, 0));
            });
        }
        req.setAttribute("products", products);
        req.getRequestDispatcher("/admin/products.jsp").forward(req, resp);
    }


    public void ShowAddProduct() throws ServletException, IOException {
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> categories = categoryDAO.getAll();
        if (categories != null) {
            categories.sort((o1, o2) -> {
                int compareValue = o1.getName().compareTo(o2.getName());
                return (Integer.compare(compareValue, 0));
            });
        }
        req.setAttribute("action", "add");
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/admin/update-product.jsp").forward(req, resp);
    }

    public void ShowHomePage() throws ServletException, IOException {
        ProductDAO productDAO = new ProductDAO();

        req.setAttribute("action", "add");
        req.setAttribute("bestSellers", productDAO.getBestSeller());
        req.setAttribute("newArrivals", productDAO.getNewArrival());
        req.setAttribute("hotSales", productDAO.getHotSale());

        req.getRequestDispatcher("/web/index.jsp").forward(req, resp);

    }

    public void AddProduct() throws ServletException, IOException {
        String messageBody = "", messageType = "";
        try {
            Product product = new Product();
            Part part = req.getPart("image");
            readData(product);
            product.setSold(0);
            if (product.isPropertiesValid()) {
                try {
                    // Save image
                    String imageName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    String now = CommonUtil.getImgDir();
                    String realPath = req.getServletContext().getRealPath("/images" + now);
                    // Check if paths exist; if not, create a new one
                    Path path = Paths.get(realPath);
                    if (!Files.exists(path)) {
                        Files.createDirectories(path);
                    }
                    part.write(realPath + "/" + imageName);
                    String image = String.format("images%s/%s", now, imageName);
                    product.setImage(image);
                    // ------------------

                    productDAO.insert(product);
                    messageBody = "A new product was created successfully!";
                    messageType = "success";
                } catch (Exception ex) {
                    ex.printStackTrace();
                    messageBody = "An error occurred when updating product's info! Please try again";
                    messageType = "danger";
                }
            } else {
                messageBody = "All fields cannot be empty!";
                messageType = "danger";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            messageBody = "An error occurred when transfer data! Please try again";
            messageType = "danger";
        }
        message.setBody(messageBody);
        message.setType(messageType);
        req.setAttribute("message", message);
        ShowAddProduct();
    }

    public void ShowUpdateProduct() throws ServletException, IOException {
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> categories = categoryDAO.getAll();
        int id = req.getParameter("id") == null ? 0 : Integer.parseInt(req.getParameter("id"));
        Product product = productDAO.get(id);
        req.setAttribute("product", product);
        req.setAttribute("categories", categories);
        req.setAttribute("action", "update");
        req.getRequestDispatcher("/admin/update-product.jsp").forward(req, resp);
    }

    public void getProductDetail() throws ServletException, IOException {
        ProductDAO productdao = new ProductDAO();
        int id = Integer.parseInt(req.getParameter("id"));
        int categoryID = Integer.parseInt(req.getParameter("CategoryID"));
        Product product = productdao.get(id);
        List<Product> products = get4ProdcutbyCategory(categoryID);
        req.setAttribute("product", product);
        req.setAttribute("products", products);

        req.getRequestDispatcher("/web/shop-details.jsp").forward(req, resp);
    }

    public List<Product> get4ProdcutbyCategory(int CategoryID) {
        List<Product> products = null;
        products = productDAO.getTopbyCategory(CategoryID);
        return products;
    }

    public void UpdateProduct() throws ServletException, IOException {
        String messageBody = "", messageType = "";
        int id = Integer.parseInt(req.getParameter("id"));
        Product existedProduct = productDAO.get(id);
        Product product = new Product();
        readData(product);
        product.setSold(existedProduct.getSold());
        if (product.isPropertiesValid() && existedProduct != null) {
            try {
                // Assign the new image and delete the old if you upload another image.
                Part part = req.getPart("image");
                if (!part.getSubmittedFileName().isEmpty()) {
                    String imageName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    String now = CommonUtil.getImgDir();
                    String realPath = req.getServletContext().getRealPath("/images" + now);
                    Path path = Paths.get(realPath);
                    if (!Files.exists(path)) {
                        Files.createDirectories(path);
                    }
                    part.write(realPath + "/" + imageName);
                    String image = String.format("images%s/%s", now, imageName);
                    product.setImage(image);

                    // Delete existed image
                    CommonUtil.deleteDir(req.getServletContext().getRealPath(existedProduct.getImage()));
                } else {
                    // if not upload, keep the existed image
                    product.setImage(existedProduct.getImage());
                }
                product.setId(id);
                productDAO.update(product);
                messageBody = "Product's info was changed successfully!";
                messageType = "success";
            } catch (Exception ex) {
                messageBody = "An error occurred when creating a new product! Please try again";
                messageType = "danger";
                ex.printStackTrace();
            }
        } else {
            messageBody = "All fields cannot be empty!";
            messageType = "danger";
        }
        message.setBody(messageBody);
        message.setType(messageType);
        req.setAttribute("message", message);
        ShowUpdateProduct();
    }

    public void DeleteProduct() throws ServletException, IOException {
        String messageBody = "", messageType = "";
        int id = Integer.parseInt(req.getParameter("id"));
        if (id != 0) {
            System.out.println("Id: " + id);
            Product product = productDAO.get(id);
            if (product != null) {
                if (product.getOrderItems().isEmpty() && product.getCartItems().isEmpty()) {
                    // delete image
                    CommonUtil.deleteDir(req.getServletContext().getRealPath(product.getImage()));
                    productDAO.delete(id);
                    messageBody = "Product was deleted successfully!";
                    messageType = "primary";
                } else {
                    messageBody = "Cannot delete this category, this product is already in some cart or order.";
                    messageType = "danger";
                }
            } else {
                messageBody = "Product doesn't exist";
                messageType = "danger";
            }
        } else {
            messageBody = "Id doesn't exist";
            messageType = "danger";
        }
        message.setBody(messageBody);
        message.setType(messageType);

        req.setAttribute("message", message);
        req.setAttribute("title", "Delete Information");
        req.setAttribute("action", "/admin/product");
        req.getRequestDispatcher("/admin/information.jsp").forward(req, resp);
    }

    public void GetProductPage() throws ServletException, IOException {
        String key = req.getParameter("key");
        String page = req.getParameter("page");
        String price = req.getParameter("price");
        String cateIdReq = req.getParameter("cateid");
        String size = req.getParameter("size");
        String sort = req.getParameter("sort");
        User user = (User) req.getSession().getAttribute("userLogged");
        HttpSession session = req.getSession();
        Cart cart = cartDAO.findByUser(user.getUsername());

        double from = 0.0, to = 999999999.0;
        if (key == null) {
            key = "";
        }
        if (page == null) {
            page = "1";
        }
        if (cateIdReq == null) {
            cateIdReq = "";
        }
        if (size == null) {
            size = "";
        }
        if (sort == null) {
            sort = "0";
        }
        if (price == null) {
            price = "-1";
        }
        switch (price) {
            case "1":
                from = 0;
                to = 50;
                break;
            case "2":
                from = 50;
                to = 100;
                break;

            case "3":
                from = 100;
                to = 150;
                break;

            case "4":
                from = 150;
                to = 200;
                break;

            case "5":
                from = 200;
                to = 250;
                break;

            case "6":
                from = 250;
                to = 999999999;
                break;

            default:
                from = 0;
                to = 999999999;
                break;

        }
        List<Category> categories = categoryDAO.getAll();

        List<Product> products =
                productDAO.getProductPaging((Integer.parseInt(page) - 1) * 9, 9, cateIdReq, size, key, from, to, Integer.parseInt(sort));
        int count = productDAO.count;

        req.setAttribute("typesearch", count + " results found ");

        req.setAttribute("products", products);
        System.out.print(count);
        req.setAttribute("countP", count / 9);

        req.setAttribute("total", cart.getTotal());
        req.setAttribute("quantity", cart.getCartItems().size());

        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/web/shop.jsp").forward(req, resp);
    }
//  public void GetProductAjax() throws IOException {
//
//    String exist = req.getParameter("exist");
//    int amount = 0 ;
//    if (exist!= null){
//      amount = Integer.parseInt(exist);
//    }
//    resp.setCharacterEncoding("UTF-8");
//    List<Product> products = productDAO.getProductPaging(amount, 9);
//
//
//    PrintWriter out = resp.getWriter();
//        for (Product product : products) {
//            out.println("       <div class=\"col-lg-4 col-md-6 col-sm-6\">\n" +
//                    "                     <div class=\"product__item "+(product.getDiscount()>0?"sale":"") +"\">\n" +
//                    "                        <div class=\"product__item__pic set-bg\" data-setbg=\"./web/assets/img/product/product-2.jpg\">\n" +
//                    "                           <ul class=\"product__hover\">\n" +
//                    "                              <li><a href=\"#\"><img src=\"./web/assets/img/icon/heart.png\" alt=\"\"></a></li>\n" +
//                    "                              <li><a href=\"#\"><img src=\"./web/assets/img/icon/compare.png\" alt=\"\"> <span>Compare</span></a>\n" +
//                    "                              </li>\n" +
//                    "                              <li><a href=\"#\"><img src=\"./web/assets/img/icon/search.png\" alt=\"\"></a></li>\n" +
//                    "                           </ul>\n" +
//                    "                        </div>\n" +
//                    "                        <div class=\"product__item__text\">\n" +
//                    "                           <h6>"+product.getName()+"</h6>\n" +
//                    "                           <a href=\"#\" class=\"add-cart\">+ Add To Cart</a>\n" +
//                    "                           <div class=\"rating\">\n" +
//                    "                              <i class=\"fa fa-star-o\"></i>\n" +
//                    "                              <i class=\"fa fa-star-o\"></i>\n" +
//                    "                              <i class=\"fa fa-star-o\"></i>\n" +
//                    "                              <i class=\"fa fa-star-o\"></i>\n" +
//                    "                              <i class=\"fa fa-star-o\"></i>\n" +
//                    "                           </div>\n" +
//                    "                           <h5>"+product.getPrice()+"</h5>\n" +
//                    "                           <div class=\"product__color__select\">\n" +
//                    "\n" +
//                    "                              <label class=\"active "+product.getColor()+"\" for=\"pc-5\">\n" +
//                    "                                 <input type=\"radio\" id=\"pc-5\">\n" +
//                    "                              </label>\n" +
//                    "\n" +
//                    "                           </div>\n" +
//                    "                        </div>\n" +
//                    "                     </div>\n" +
//                    "                  </div>");
//        }
//
//  }

    public void FilterProduct() {
        String exist = req.getParameter("exist");
//    String exist = req.getParameter("exist");

    }
}