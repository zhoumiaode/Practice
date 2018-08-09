package com.example.test.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

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

    @MessageMapping(value = "/welcome")
    @SendTo("/topic/getResponse") //当服务器有消息时会对订阅了@SendTo中的路径的游览器发送消息
    public WiselyResponse say(WiselyMessage message) throws InterruptedException {

        Thread.sleep(3000);
        return new WiselyResponse("Welcome,"+message.getName());

    }
}
