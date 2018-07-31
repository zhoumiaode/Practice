package com.example.test;

import com.example.test.Filter.JwtFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.example.test.mapper")
@ComponentScan("com.example.test.*")
//@EnableScheduling //表示开启定时任务的支持
@EnableAsync  //表示开启异步执行的支持

public class TestApplication {
    //过滤器
   /* @Bean
    protected FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/api/*");
        return registrationBean;
    }*/

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);

	}
}
