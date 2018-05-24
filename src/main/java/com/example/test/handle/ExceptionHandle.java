package com.example.test.handle;

import com.example.test.domain.Result;
import com.example.test.exception.GirlException;
import com.example.test.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;


@ControllerAdvice
public class ExceptionHandle {

    private static final Logger logger=LoggerFactory.getLogger(ExceptionHandle.class);

    @InitBinder
    public void Init(WebDataBinder binder){
       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

    }

    @ModelAttribute
    public void addAttribute(Model model){
        model.addAttribute("name","jack");

    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result Handle(Exception e){

        if(e instanceof GirlException){
            GirlException girlException=(GirlException) e;
            return ResultUtil.error(girlException.getCode(),girlException.getMessage());
        }else {
            logger.error("系统异常{}",e);
            return ResultUtil.error(-1,e.getMessage());
        }
    }
}
