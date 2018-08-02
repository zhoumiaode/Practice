package com.example.test.Intercepter;

import com.example.test.domain.Girls;
import com.example.test.service.GirlsService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName: test
 * @Package: com.example.test.Intercepter
 * @ClassName: TestIntercepter
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/02 15:04
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/02 15:04
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class TestIntercepter implements HandlerInterceptor {

    @Resource
    private GirlsService girlsService;
    //在请求处理之前进行调用（Controller方法调用之前
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.printf("preHandle被调用");
        return true;    //如果false，停止流程，api被拦截
    }

    //请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        Girls girl=null;
        girl=girlsService.findByID(1);
        if(girl==null){
            System.out.println("postHandle被调用失败");
        }else{
            System.out.println("postHandle被调用成功");
        }
    }

    //在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion被调用");
    }
}
