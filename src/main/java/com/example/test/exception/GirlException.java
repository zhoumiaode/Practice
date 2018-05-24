package com.example.test.exception;

import com.example.test.enums.ResultEnums;

public class GirlException extends RuntimeException {

    private Integer code;

    public  GirlException(ResultEnums resulenums){
        super(resulenums.getMsg());
        this.code=resulenums.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
