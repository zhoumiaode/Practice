package com.example.test.controller.controller;

import com.example.test.properties.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {


    @Autowired
    private GirlProperties girlProperties;

    @RequestMapping(value="/hello",method= RequestMethod.GET)
    public String  say(ModelMap model) {
        model.addAttribute("name","jake");
        return "index";
    }
}
