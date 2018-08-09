package com.example.test.Intercepter;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

/**
 * @ProjectName: test
 * @Package: com.example.test.Intercepter
 * @ClassName: MyWebMvcConfigurerAdapter
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/02 15:13
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/02 15:13
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurationSupport {

    @Bean
    public  TestIntercepter testIntercepter() {
        return new TestIntercepter();
    }

    /**
     * 配置静态资源
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
        super.addResourceHandlers(registry);
    }

    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns 用于添加拦截规则
        //excludePathPatterns 用于排除拦截
        registry.addInterceptor(testIntercepter()).addPathPatterns("/**")
                .excludePathPatterns("/hlladmin/login") //登录页
                .excludePathPatterns("/hlladmin/user/sendEmail") //发送邮箱
                .excludePathPatterns("/hlladmin/user/register") //用户注册
                .excludePathPatterns("/hlladmin/user/login"); //用户登录
        super.addInterceptors(registry);
    }

    /** 
    * @Description:  设置控制器的返回视图
    * @Param: [registry]
    * @return: void 
    * @Author: zhoumiaode
    * @Date: 2018/08/03 
    */ 
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("upload").setViewName("hello");
        registry.addViewController("sse").setViewName("sse");
        registry.addViewController("ws").setViewName("ws");
        System.out.println("222222222222222222222222222");
    }

    /**
    * @Description:配置访问jsp页面路径
    * @Param: []
    * @return: org.springframework.web.servlet.view.InternalResourceViewResolver
    * @Author: zhoumiaode
    * @Date: 2018/08/03
    */
    @Bean
    public InternalResourceViewResolver viewResolver(){

        InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    /** 
    * @Description: 定义自定义的HttpMessageConverter
    * @Param: [converters]
    * @return: void 
    * @Author: zhoumiaode
    * @Date: 2018/08/08 
    */ 
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters){

    }
}
