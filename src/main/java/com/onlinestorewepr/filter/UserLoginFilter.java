package com.onlinestorewepr.filter;

import com.onlinestorewepr.dao.CartDAO;
import com.onlinestorewepr.entity.Cart;
import com.onlinestorewepr.entity.User;
import com.onlinestorewepr.service.CartService;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/profile","/update-profile","/cart-item/*","/shop","/order/*"})
public class UserLoginFilter extends HttpFilter implements Filter {
//    private static final String[] loginRequireURLs = {"web/profile","web/update-profile"};

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request =  req;
        HttpServletResponse response = resp;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userLogged");

        if(user !=null){
            chain.doFilter(request,response);
        }
        else {
            request.getRequestDispatcher("/web/authentication.jsp").forward(request,response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}
