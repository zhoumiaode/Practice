package com.example.test.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.security.Principal;

/**
 * @ProjectName: test
 * @Package: com.example.test.websocket
 * @ClassName: WsController
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/09 10:16
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/09 10:16
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Controller
public class WsController {
    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping(value = "/welcome")
    @SendTo("/topic/getResponse") //当服务器有消息时会对订阅了@SendTo中的路径的游览器发送消息
    public WiselyResponse say(WiselyMessage message) throws InterruptedException {

        Thread.sleep(3000);
        return new WiselyResponse("Welcome,"+"12345");
    }


    @MessageMapping(value = "/chat")
    public void handleChat(Principal principal,String msg) {
        if(principal.getName().equals("wyf")){
            //第一个参数是发送的用户，第二个参数是游览器订阅的地址，第三个参数是要发送的消息
            simpMessagingTemplate.convertAndSendToUser("wisely","/queue/notifications",principal.getName()+"-send:"+msg);
        }else{
            simpMessagingTemplate.convertAndSendToUser("wyf","/queue/notifications",principal.getName()+"-send:"+msg);
        }
    }
}
