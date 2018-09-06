package com.example.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: test
 * @Package: com.example.test.controller
 * @ClassName: SpringSessionController
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/09/05 14:11
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/09/05 14:11
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@RestController
public class SpringSessionController {

    @GetMapping(value = "/putSession")
    public void put(){
        ServletRequestAttributes requestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=requestAttributes.getRequest();
        HttpServletResponse response=requestAttributes.getResponse();
        HttpSession session=request.getSession();
        session.setAttribute("sessionId","1");
    }
    @GetMapping(value = "/getSession")
    public Object get(HttpServletRequest request){
        HttpSession session=request.getSession();
        Map<String, Object> sessionIdMap = new HashMap<String, Object>();
        String strSessionId = request.getSession().getId();
        int iPort = request.getServerPort();
        String value= (String) session.getAttribute("sessionId");
        sessionIdMap.put("服务器端口：", iPort);
        sessionIdMap.put("sessionId：", strSessionId);
        sessionIdMap.put("value",value);
        return sessionIdMap ;
    }

}
