/*
package com.example.test.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

*/
/**
 * @ProjectName: test
 * @Package: com.example.test.websocket
 * @ClassName: WebSocketConfig
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/09 10:07
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/09 10:07
 * @UpdateRemark: The modified content
 * @Version: 1.0
 *//*

@Configuration
@EnableWebSocketMessageBroker //开启使用STOMP协议来传输基于代理的消息，此时控制器对应使用@MessageMapping
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    */
/**
    * @Description:  注册STOMP协议的节点，并映射指定的URL
    * @Param: [registry] 
    * @return: void 
    * @Author: zhoumiaode
    * @Date: 2018/08/09 
    *//*

    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册一个STOMP的endpoint,并指定使用SockJS协议
        registry.addEndpoint("/endpointWisely").withSockJS();
        registry.addEndpoint("/endpointChat").withSockJS();
    }

    */
/**
    * @Description:  配置消息代理
    * @Param: [registry] 
    * @return: void 
    * @Author: zhoumiaode
    * @Date: 2018/08/09 
    *//*

    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //广播式应配置消息代理
        //registry.enableSimpleBroker("/topic");
        registry.enableSimpleBroker("/queue","/topic");
    }

}
*/
