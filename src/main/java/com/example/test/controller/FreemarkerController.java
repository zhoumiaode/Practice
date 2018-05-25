package com.example.test.controller;

import com.example.test.properties.GirlProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "free")
public class FreemarkerController {

    @Resource
    private GirlProperties girlProperties;


    @GetMapping(value = "getFree")
    public String getFree(ModelMap modelMap){

        modelMap.addAttribute("girlProperties",girlProperties);
        System.out.println(1);
        return "freemarker";
    }
}
