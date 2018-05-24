package com.example.test.controller;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ReadFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest htp= (HttpServletRequest) request;
        HttpSession se=htp.getSession();
        se.setAttribute("name","jack");

    }

    @Override
    public void destroy() {

    }
}
