package com.onlinestorewepr.service;

import com.onlinestorewepr.dao.*;
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
    private SellerDAO sellerDAO;
    private MessageUtil message;
    private UserDAO userDAO;

  public ProductService(HttpServletRequest req, HttpServletResponse resp) {
    this.req = req;
    this.resp = resp;
    this.userDAO = new UserDAO();
    this.productDAO = new ProductDAO();
    this.categoryDAO = new CategoryDAO();
    this.cartDAO = new CartDAO();
    this.sellerDAO = new SellerDAO();
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
      User seller = userDAO.get(req.getParameter("username"));
      boolean available = req.getParameter("available") == null || (req.getParameter("available").equals("1"));
      String name = req.getParameter("name");
      String image = "temp";
      String description = req.getParameter("description");
      double price = Integer.parseInt(req.getParameter("price"));
      double discount = Integer.parseInt(req.getParameter("discount"));
      int quantity = Integer.parseInt(req.getParameter("quantity"));
      String size = req.getParameter("size");
      String color = req.getParameter("color");
      String brand = req.getParameter("brand");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("userLogged");
      product.setAvailable(available);
      product.setCategory(category);
      product.setSeller(user.getSeller());
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
    }}

    public void ShowUpdateProductFromSeller() throws ServletException, IOException {
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> categories = categoryDAO.getAll();
        int id = req.getParameter("id") == null ? 0 : Integer.parseInt(req.getParameter("id"));
        Product product = productDAO.get(id);
        req.setAttribute("product", product);
        req.setAttribute("categories", categories);
        req.setAttribute("action", "update");
        req.getRequestDispatcher("/seller/update-product.jsp").forward(req, resp);
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
      List<Product> products = getBySeller(product.getSeller().getSellerId());
      req.setAttribute("product", product );
      req.setAttribute("products", products);

      req.getRequestDispatcher("/web/shop-details.jsp").forward(req, resp);

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
    public void ListProductsInSeller() throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("userLogged");
      if (user.getSeller()== null){
          req.getRequestDispatcher("/web/index.jsp").forward(req, resp);
      }
        List<Product> products = productDAO.getBySeller(user.getSeller().getSellerId());

        if (products != null) {
            products.sort((o1, o2) -> {
                int compareValue = o1.getName().compareTo(o2.getName());
                return (Integer.compare(compareValue, 0));
            });
        }
        req.setAttribute("products", products);
        req.getRequestDispatcher("/seller/products.jsp").forward(req, resp);
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
        req.getRequestDispatcher("/seller/update-product.jsp").forward(req, resp);
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





    public List<Product> get4ProdcutbyCategory(int CategoryID) {
        List<Product> products = null;
        products = productDAO.getTopbyCategory(CategoryID);
        return products;
    }
    public List<Product> getBySeller(int sellerId) {
        List<Product> products = null;
        products = productDAO.getBySeller(sellerId);
        return products;
    }

    public void UpdateProductFromSeller() throws ServletException, IOException {
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
        ShowUpdateProductFromSeller();
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

    int from = 0 , to = 999999999;
    if (key== null){
      key= "";
    }
    if (page== null){
      page= "1";
    }
    if (cateIdReq== null){
      cateIdReq= "";
    }
    if (size== null){
      size= "";
    }
    if (sort == null){
      sort   ="0";
    }
    if (price==null){
      price = "-1";
    }
    switch (price){
      case "1":
        from=0;
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
        from = 0 ;
        to = 999999999;
        break;

    }
    List<Category> categories = categoryDAO.getAll();

    List<Product> products =
            productDAO.getProductPaging((Integer.parseInt(page) -1)*9, 9, cateIdReq, size,key,from , to , Integer.parseInt(sort));
    int count  = productDAO.count;

    req.setAttribute("typesearch",count + " results found ");

    req.setAttribute("products", products);
    System.out.print(count);
    req.setAttribute("countP", count/9);

    req.setAttribute("total", cart.getTotal());
    req.setAttribute("quantity", cart.getCartItems().size());

    req.setAttribute("categories", categories);
    req.getRequestDispatcher("/web/shop.jsp").forward(req, resp);
  }

    public void FilterProduct() {
        String exist = req.getParameter("exist");
//    String exist = req.getParameter("exist");

    }
}