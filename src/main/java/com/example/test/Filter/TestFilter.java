package com.example.test.Filter;


import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ProjectName: test
 * @Package: com.example.test.Filter
 * @ClassName: TestFilter
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/02 14:59
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/02 14:59
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */

@Order(1)
@WebFilter(filterName = "TestFilter",urlPatterns = "/*")
public class TestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res=(HttpServletResponse)response;
        String path=req.getServletPath();
        HttpSession session=req.getSession(false);
        if(path.equals("/static/login.html")||path.equals("/putSession")) {
            chain.doFilter(req, res);
            return;
        }else{
            if(session!=null&&session.getAttribute("sessionId")!=null){
                chain.doFilter(request,response);
            }else{
                RequestDispatcher rd=request.getRequestDispatcher("/static/loginIn.html");
                rd.forward(request,response);
            }
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        System.out.println("过滤器死亡");
    }
}
