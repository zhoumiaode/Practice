package com.example.test.controller;

import com.example.test.domain.Result;
import io.jsonwebtoken.Claims;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "role", method = RequestMethod.GET)
    public Result login(final HttpServletRequest request) throws ServletException {
        final Claims claims = (Claims) request.getAttribute("claims");
        claims.forEach((key, value) -> {
            System.out.println("key:"+key+",value:"+value);
        });
        System.out.println(claims.get("roles"));
        return new Result(000,"成功",null);
    }
}
