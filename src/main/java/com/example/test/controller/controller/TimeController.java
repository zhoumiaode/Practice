package com.example.test.controller.controller;

import com.example.test.domain.Time;
import com.example.test.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TimeController {

    public Logger logger= LoggerFactory.getLogger(TimeController.class);


    @GetMapping(value = "time")
    public Object addTime(Time time){
        System.out.println(time.getDates());

        return ResultUtil.Success(time.getDates());
    }
}
