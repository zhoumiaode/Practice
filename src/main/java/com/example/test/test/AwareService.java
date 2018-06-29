package com.example.test.test;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

public class AwareService implements BeanNameAware,ResourceLoaderAware{
    private String beanName;
    private ResourceLoader loader;
    @Override
    public void setBeanName(String name) {

        this.beanName=name;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {

        this.loader=resourceLoader;
    }

    public void outputResult(){
        System.out.println("Bean的名称是:",beanName);
    }
}
