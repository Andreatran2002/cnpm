package com.onlinestorewepr.service;

import com.onlinestorewepr.dao.CartDAO;
import com.onlinestorewepr.dao.ProductDAO;
import com.onlinestorewepr.dao.SellerDAO;
import com.onlinestorewepr.dao.UserDAO;
import com.onlinestorewepr.entity.Seller;
import com.onlinestorewepr.entity.User;
import com.onlinestorewepr.util.CommonUtil;
import com.onlinestorewepr.util.MessageUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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

public class SellerService {
    private SellerDAO sellerDAO;
    private UserDAO userDAO;
    private ProductDAO productDAO;
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private MessageUtil message;
    private ServiceResult serviceResult;


    public SellerService(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
        this.sellerDAO = new SellerDAO();
        this.message = new MessageUtil();
        userDAO = new UserDAO();
        sellerDAO = new SellerDAO();
        serviceResult = new ServiceResult();
    }



    public void showProfile() throws ServletException, IOException {
        Seller sellerLogged = (Seller) req.getSession().getAttribute("sellerLogged");
        Seller seller = new SellerDAO().get(sellerLogged.getSellerId());
        req.setAttribute("seller", seller);
        resp.setContentType("text/html;charset=UTF-8");
        req.getRequestDispatcher("/seller/profile.jsp").forward(req,resp);
    }
    public void RegisterSeller() throws ServletException, IOException {
        try{
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("userLogged");
            if (user!= null){
                if (user!= null){
                    Seller seller = new Seller();
                    seller.setSellerName(user.getUsername());
                    seller.setUser(user);
                    seller.setStatus(1);
                    seller.setImage("asset/default.png");

                    sellerDAO.insert(seller);
//                    System.out.print();
//                    User savedUser = userDAO.get(seller.getUser().getUsername());

                    user.setSeller(sellerDAO.getBySellerName(seller.getSellerName()));

                    userDAO.update(user);
                    req.getRequestDispatcher("/logout").forward(req,resp);

                }
            }
            else {
                req.getRequestDispatcher("/").forward(req,resp);
            }

        }catch (Exception e){
            req.getRequestDispatcher("/").forward(req,resp);
            e.printStackTrace();
        }


    }


    public void updateSellerProfile() throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        Seller seller = (Seller) req.getSession().getAttribute("userd");
        editSellerProfile(seller);
        sellerDAO.update(seller);
        req.getRequestDispatcher("/seller/account-profile.jsp").forward(req,resp);
    }

    public void editSellerProfile(Seller seller) throws IOException, ServletException {
        String sellername = req.getParameter("sellername");
        Integer status = Integer.valueOf(req.getParameter("status"));
        Part part = req.getPart("image");

        seller.setSellerName(sellername);
        seller.setStatus(status);

        //Update image
        if (!part.getSubmittedFileName().isEmpty()){
            String imageName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            String now = CommonUtil.getImgDir();
            String realPath = req.getServletContext().getRealPath("/imagesAvatar" + now);
            Path path = Paths.get(realPath);

            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            part.write(realPath + "/" + imageName);
            String image = String.format("imagesAvatar%s/%s", now, imageName);
            System.out.println(image);
            seller.setImage(image);
        }
    }

    public void ListSeller()throws ServletException, IOException{
        List<Seller> accounts = sellerDAO.getAll();
        req.setAttribute("accounts", accounts);
        req.getRequestDispatcher("/seller/accounts.jsp").forward(req, resp);
    }

    public void ShowEditSeller()throws ServletException, IOException{
        Integer sellerid = Integer.valueOf(req.getParameter("sellerid"));
        if (sellerid != null) {
            Seller account = sellerDAO.get(sellerid);
            req.setAttribute("account", account);
            req.setAttribute("action", "edit");
            req.getRequestDispatcher("/seller/update-account.jsp").forward(req, resp);
        }
    }

    public User authenticate(String username, String password) {
        User user = userDAO.get(username);
        if (user != null && user.getSeller() != null ) {
            if (password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public void sellerLogin() throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = authenticate(username, password);
        User userCreated = userDAO.get(username);
        String errMessage = "";
        boolean hasError = false;

        if (username == null || password == null || username.length() == 0 || password.length() == 0) {
            hasError = true;
            errMessage = "Username & Password cannot be empty!";
        } else {
            try {
                if (user == null) {
                    hasError = true;
                    errMessage = "Username or Password is incorrect!";
                }
                if (userCreated == null) {
                    hasError = true;
                    errMessage = "Account does not exist!";
                }
            } catch (Exception e) {
                e.printStackTrace();
                hasError = true;
                errMessage = e.getMessage();
            }
        }

        if (hasError) {
            req.setAttribute("message", errMessage);
            req.getRequestDispatcher("/seller/login.jsp").forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("userLogged", user);
            session.setMaxInactiveInterval(1000);
            resp.sendRedirect("/seller/index.jsp");
        }
    }

    public void EditSeller()throws ServletException, IOException{
        Integer sellerid = Integer.valueOf(req.getParameter("sellerid"));
        Seller seller = sellerDAO.get(sellerid);
        DiskFileItemFactory diskFileItemFactory = new
                DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new
                ServletFileUpload(diskFileItemFactory);
        servletFileUpload.setHeaderEncoding("UTF-8");
        try {
            resp.setContentType("text/html");
            req.setCharacterEncoding("UTF-8");
            List<FileItem> items = servletFileUpload.parseRequest(req);
            for (FileItem item : items) {
                if (item.getFieldName().equals("account-fullname")) {
                    seller.setSellerName(item.getString("UTF-8"));
                }
                if (item.getFieldName().equals("account-status")) {
                    seller.setStatus(Integer.valueOf(item.getString("UTF-8")));
                }

            }
            sellerDAO.update(seller );
            resp.sendRedirect(req.getContextPath() + "/seller/accounts");
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ShowHomePage() throws ServletException, IOException {
        req.getRequestDispatcher("/seller/index.jsp").forward(req, resp);

    }
}
