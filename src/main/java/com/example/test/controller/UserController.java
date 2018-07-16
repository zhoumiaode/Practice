package com.example.test.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.web.bind.annotation.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/user")
public class UserController {


    @RequestMapping(value = "login", method = RequestMethod.POST)
    public LoginResponse login(@RequestParam("name")String name,@RequestParam("password")String password)
            throws ServletException {
        if (name == null) {
            throw new ServletException("Invalid login");
        }

        //加密生成token
        return new LoginResponse(Jwts.builder().setSubject(name)
                .claim("roles", name).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact());
    }

    @SuppressWarnings("unused")
    private static class LoginResponse {
        public String token;

        public LoginResponse(final String token) {
            this.token = token;
        }
    }

    public void method(){

    }
}
