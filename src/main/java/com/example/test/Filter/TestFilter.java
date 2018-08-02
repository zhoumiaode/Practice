package com.example.test.Filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

@WebFilter(filterName = "TestFilter",urlPatterns = "/*")
public class TestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res=(HttpServletResponse)response;
        System.out.println("过滤器执行中");
        chain.doFilter(req,res);
    }

    @Override
    public void destroy() {
        System.out.println("过滤器死亡");
    }
}
