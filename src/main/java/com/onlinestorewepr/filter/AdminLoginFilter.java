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

@WebFilter(urlPatterns = {"/admin/product/*","/admin/category/*","/admin/order/*","/admin/account/*"})
public class AdminLoginFilter extends HttpFilter implements Filter {
//    private static final String[] loginRequireURLs = {"web/profile","web/update-profile"};

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("adminLogged");
        if(obj!=null){
            chain.doFilter(request,response);
        }
        else {
            request.getRequestDispatcher("/login-admin").forward(request,response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}