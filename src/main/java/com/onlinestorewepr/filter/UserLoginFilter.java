package com.onlinestorewepr.filter;

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

@WebFilter(urlPatterns = {"/profile","/update-profile","/cart-item/*","/shop"})
public class UserLoginFilter extends HttpFilter implements Filter {
//    private static final String[] loginRequireURLs = {"web/profile","web/update-profile"};

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request =  req;
        HttpServletResponse response = resp;
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("userLogged");
        if(obj!=null){
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
