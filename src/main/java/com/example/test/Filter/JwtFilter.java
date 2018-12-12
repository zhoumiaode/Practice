package com.example.test.Filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

/*@Order(1)
@WebFilter(filterName = "JwtFilter",urlPatterns = "/apia/*")*/
public class JwtFilter extends GenericFilterBean implements Filter{

    @Override
    public void destroy() {
        System.out.println("过滤器被销毁");
    }

        @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse res,
                         final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse)res;
        String path = request.getContextPath();
        String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path;
        //客户端将token封装在请求头中，格式为（Bearer后加空格）：Authorization：Bearer +token
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ServletException("Missing or invalid Authorization header.");

        }

        //去除Bearer 后部分
        final String token = authHeader.substring(7);

        try {
            //解密token，拿到里面的对象claims
            final Claims claims = Jwts.parser().setSigningKey("secretkey")
                    .parseClaimsJws(token).getBody();
            //将对象传递给下一个请求
            request.setAttribute("claims", claims);
        }
        catch (final SignatureException e) {
            throw new ServletException("Invalid token.");
        }

        chain.doFilter(req, res);
    }

    /*@Override
    public void init(FilterConfig arg0) throws ServletException{
        System.out.println("过滤器被初始化");
    }*/
}
