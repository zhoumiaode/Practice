package com.example.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
