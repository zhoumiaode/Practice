package com.example.test.utils;

import com.example.test.domain.Result;

public class ResultUtil {

    public  static Result Success(Object object){
        Result result=new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public  static Result Success(){

        return Success(null);
    }

    public  static Result error(Integer code,String msg){
        Result result=new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
