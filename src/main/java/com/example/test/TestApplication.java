package com.example.test;

import com.example.test.Filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

@SpringBootApplication
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
