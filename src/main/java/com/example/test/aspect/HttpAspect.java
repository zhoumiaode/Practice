package com.example.test.aspect;

import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class HttpAspect {

    public final static Logger logger= LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.example.test.controller.GirlController.*(..))")
    public void log(){

    }

    @Before("log()")
    public void DoBefore(JoinPoint joinPoint){

        ServletRequestAttributes attributes=(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        //url
        logger.info("url={}",request.getRequestURL());
        //method
        logger.info("method={}",request.getMethod());
        //ip
        logger.info("ip={}",request.getRemoteAddr());
        //类方法
        logger.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        //参数


        logger.info("args={}",joinPoint.getArgs());
        logger.info("Before()执行方法");

    }

    @After("log()")
    public void DoAfter(){
        System.out.printf("After()执行方法");
    }

    @AfterReturning(returning = "object",pointcut="log()")
    public void doAfterReturning(Object object){

        logger.info("response={}",object.toString());
    }
}
