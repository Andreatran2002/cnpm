package com.onlinestorewepr.service;

import com.onlinestorewepr.dao.ProductDAO;
import com.onlinestorewepr.dao.UserDAO;
import com.onlinestorewepr.entity.CartItem;
import com.onlinestorewepr.entity.Category;
import com.onlinestorewepr.entity.Product;
import com.onlinestorewepr.entity.User;
import com.onlinestorewepr.util.MessageUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserService {
    private UserDAO userDAO;
    private ServiceResult serviceResult;
    private MessageUtil message;

    HttpServletResponse resp;
    HttpServletRequest req;

    public UserService(HttpServletRequest req, HttpServletResponse resp)
    {
        this.req = req;
        this.resp = resp;
        this.message = new MessageUtil();
        userDAO = new UserDAO();
    }

    public User authenticate(String username, String password) {
        User user = userDAO.get(username);
        if (user != null && !user.isAdmin()) {
            if (password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public void userRegister() throws IOException,ServletException{
        String name = req.getParameter("fullName");
        String username = req.getParameter("usernameNew");
        String password = req.getParameter("passwordNew");
        String phone =req.getParameter("phone");
        String gender = req.getParameter("gender");

        User userCreated = userDAO.findUserCreated(username);
        String message = "";
        if(name==null || phone==null ||username == null ||password==null || gender == null){
            if (Objects.equals(name, ""))
            {
                message ="Vui lòng điền họ và tên!";
            }
            else if (Objects.equals(username, ""))
            {
                message ="Vui lòng điền tên đăng nhập!";
            }
            else if (Objects.equals(phone, ""))
            {
                message ="Vui lòng điền số điện thoại!";
            }
            else if (Objects.equals(password, ""))
            {
                message ="Vui lòng điền mật khẩu";
            }
            else if (gender == null)
            {
                message = "Vui lòng chọn giới tính";
            }
            req.setAttribute("messageRegisterFail",message);
            req.setAttribute("action", "signup");
            req.getRequestDispatcher("/web/authentication.jsp").forward(req,resp);
        }
        // Kiem tra so dien thoai
        boolean check = true;
        for (int i = 0; i < phone.length(); i++)
        {
            if ( !(phone.charAt(i) <= '9' && phone.charAt(i) >= '0' ) )
            {
                check = false;
                message = "Số điện thoại không hợp lệ! Vui lòng nhập lại số điện thoại!";
                req.setAttribute("messageRegisterFail",message);
                req.getRequestDispatcher("/web/authentication.jsp").forward(req,resp);
            }
        }
        if (check)
        {
            if (phone.length() != 10)
            {
                message = "Độ dài số điện thoại phải là 10 số! Vui lòng nhập lại!";
                req.setAttribute("messageRegisterFail",message);
                req.getRequestDispatcher("/web/authentication.jsp").forward(req,resp);
            }
        }

        // Kiem tra mat khau
        if (password.length() < 8)
        {
            message = "Mật khẩu phải tối thiểu 8 kí tự! Vui lòng nhập lại!";
            req.setAttribute("messageRegisterFail",message);
            req.getRequestDispatcher("/web/authentication.jsp").forward(req,resp);
        }
        if (password.contains(name))
        {
            message = "Mật khẩu không được chứa tên riêng! Vui lòng nhập lại!";
            req.setAttribute("messageRegisterFail",message);
            req.getRequestDispatcher("/web/authentication.jsp").forward(req,resp);
        }
        boolean number = false, lowercase = false, uppercase = false, special = false;
        for (int i = 0; i < password.length(); i++)
        {
            char x = password.charAt(i);
            if (x <= '9' && x >= '0') number = true;
            else if (x <= 'z' && x >= 'a') lowercase = true;
            else if (x <= 'Z' && x >= 'A') uppercase = true;
            else special = true;
        }
        if (number == false || lowercase == false || uppercase == false || special == false)
        {
            if (number == false)
            {
                message = "Mật khẩu phải bao gồm các chữ số! Vui lòng nhập lại!";
            }
            else if (lowercase == false)
            {
                message = "Mật khẩu phải bao gồm các chữ thường! Vui lòng nhập lại!";
            }
            else if (uppercase == false)
            {
                message = "Mật khẩu phải bao gồm các chữ in hoa! Vui lòng nhập lại!";
            }
            else if (special == false)
            {
                message = "Mật khẩu phải bao gồm kí tự đặc biệt! Vui lòng nhập lại!";
            }
            req.setAttribute("messageRegisterFail",message);
            req.getRequestDispatcher("/web/authentication.jsp").forward(req,resp);
        }
//      Kiểm tra xem có tồn tại hay chưa
        else if(userCreated!=null){
            message= "Tên tài khoản đã tồn tại!";
            req.setAttribute("messageRegisterFail",message);
            req.getRequestDispatcher("/web/authentication.jsp").forward(req,resp);
        }
        else {
            User userNew = new User();
            if(username != null){
                userNew.setUsername(username);

            }
            if(password!= null && !password.equals("")){
                userNew.setPassword(password);
            }
            userNew.setName(name);
            userNew.setGender(gender);
            userNew.setPhone(phone);

            userDAO.insert(userNew);

            message= "Tạo thành công! Đăng nhập để bắt đầu";
            if (message != null) {
                req.setAttribute("messageRegisterSuccess", message);
                req.getRequestDispatcher("/web/authentication.jsp").forward(req,resp);
            }
        }
    }

    protected  void saveRememberMe(HttpServletResponse response, String userName, String password){
        Cookie username = new Cookie("username",userName);
        Cookie pass = new Cookie("password",password);
        username.setMaxAge(60 * 60 * 24 * 365 * 3);
        pass.setMaxAge(60 * 60 * 24 * 365 * 3);
        response.addCookie(username);
        response.addCookie(pass);
        response.setContentType("text/html");
    }

    public void login() throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");
        String errMessage = "";
        boolean hasErr = false;

        User user = authenticate(username, password);
        boolean isRememberMe = "on".equals(remember);

        //Check login
        if(username == null ||password==null || username.length()==0 || password.length()==0){
            hasErr= true;
            errMessage ="Tên đăng nhập & Mật khẩu không được trống!";
        }
        else {
            try{
                if (user == null)
                {
                    hasErr = true;
                    errMessage ="Tên Đăng nhập hoặc Mật khẩu không đúng!";
                }
            }
            catch (Exception e){
                e.printStackTrace();
                hasErr = true;
                errMessage = e.getMessage();
            }
        }

        //Display status on web
        if(hasErr)
        {
            req.setAttribute("message",errMessage);
            req.getRequestDispatcher("/web/authentication.jsp").forward(req,resp);
        }
        else {
            //Create session when login is successful
            HttpSession session = req.getSession();
            session.setAttribute("userLogged",user);
            //Check Remember me to create Cookie
            if(isRememberMe)
            {
                saveRememberMe(resp,username,password);
            }
            Object objRedirectURL = session.getAttribute("redirectURL");
            session.setMaxInactiveInterval(1000);
            if (objRedirectURL != null) {
                String redirectURL = (String) objRedirectURL;
                session.removeAttribute("redirectURL");
                resp.sendRedirect("/home");
            } else {
              // showProfile();//

            }
        }
    }

    public void showProfile() throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.getRequestDispatcher("/web/profile.jsp").forward(req,resp);
    }

    public void showEditUserProfile() throws ServletException, IOException{
        resp.setContentType("text/html;charset=UTF-8");
        req.getRequestDispatcher("/web/edit-profile.jsp").forward(req,resp);
    }

    public void editUserProfile(User user){
        String fullName = req.getParameter("name");
        String phone = req.getParameter("phone");
        String gender = req.getParameter("sex");
        String address = req.getParameter("address");

        user.setName(fullName);
        user.setPhone(phone);
        user.setGender(gender);
        user.setAddress(address);
    }


    public void updateUserProfile() throws ServletException, IOException{
        resp.setContentType("text/html;charset=UTF-8");
        User user = (User) req.getSession().getAttribute("userLogged");
        editUserProfile(user);
        userDAO.update(user);
        showProfile();
    }
    public void ListUser()throws ServletException, IOException{
        List<User> accounts = userDAO.getAll();
        req.setAttribute("accounts", accounts);
        req.getRequestDispatcher("/admin/accounts.jsp").forward(req, resp);
    }
    public void ShowEditUser()throws ServletException, IOException{
        String username = req.getParameter("username");
        if (username != null) {
            User account = userDAO.get(username);
            req.setAttribute("account", account);
            req.setAttribute("action", "edit");
            req.getRequestDispatcher("/admin/update-account.jsp").forward(req, resp);

        }
    }
    public void EditUser()throws ServletException, IOException{
        String username = req.getParameter("username") ;
        User user = userDAO.get(username);
        DiskFileItemFactory diskFileItemFactory = new
                DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new
                ServletFileUpload(diskFileItemFactory);
        servletFileUpload.setHeaderEncoding("UTF-8");
        try {
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            req.setCharacterEncoding("UTF-8");
            List<FileItem> items = servletFileUpload.parseRequest(req);
            for (FileItem item : items) {
                if (item.getFieldName().equals("account-phone")) {
                    user.setPhone(item.getString("UTF-8"));
                }
                if (item.getFieldName().equals("account-fullname")) {
                    user.setName(item.getString("UTF-8"));
                }
                if (item.getFieldName().equals("account-address")) {
                    user.setAddress(item.getString("UTF-8"));
                }
                if (item.getFieldName().equals("account-gender")) {
                    user.setGender(item.getString("UTF-8"));
                }
            }
            userDAO.update(user );
            resp.sendRedirect(req.getContextPath() + "/admin/accounts");
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {e.printStackTrace();}
    }

    public String getLoggedUsername() {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("username")) {
                    return c.getValue();
                }
            }
        }
        return null;
    }
    public void changeUserPassword() throws ServletException,IOException{
        String username = req.getParameter("username");
        String oldPass = req.getParameter("password-old");
        String newPass = req.getParameter("password-new");
        String passRetype = req.getParameter("password-retype");
        System.out.println(username+oldPass);

        User user = authenticate(username,oldPass);
        String messageBody, messageType;

        if(user == null){
            messageBody = "Mật khẩu cũ không đúng, vui lòng nhập lại!";
            messageType = "danger";
        }
        else {
            if(Objects.equals(oldPass, newPass)){
                messageBody = "Mật khẩu mới không đuợc trùng với mật khẩu cũ!";
                messageType = "danger";
            }
            else if (!Objects.equals(newPass, passRetype)){
                messageBody = "Mật khẩu nhập lại không chính xác!";
                messageType = "danger";
            }
            else {
                resp.setContentType("text/html;charset=UTF-8");
                User usernew = (User) req.getSession().getAttribute("userLogged");
                usernew.setPassword(newPass);
                userDAO.update(usernew);
                messageBody = "Thay đổi thành công!";
                messageType = "success";
            }
        }
        message.setBody(messageBody);
        message.setType(messageType);

//        req.setAttribute("action", "add");
        req.setAttribute("message", message);
        req.getRequestDispatcher("/web/change_pass.jsp").forward(req, resp);
    }
}
