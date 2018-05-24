package com.example.test.enums;

public enum ResultEnums {
    UNKONW_ERROR(-1,"未知错误"),

    SUCCESS(0,"成功"),

    PRIMARY(100,"你可能还在上小学生"),

    MIDDLE_SCHOOL(101,"你可能还在上初中"),
    ;

    private Integer code;
    private String msg;

    ResultEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
