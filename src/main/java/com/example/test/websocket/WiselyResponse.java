package com.example.test.websocket;

/**
 * @ProjectName: test
 * @Package: com.example.test.websocket
 * @ClassName: WiselyResponse
 * @Description: 服务器向游览器发送消息用此类接受
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/09 10:15
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/09 10:15
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class WiselyResponse {

    private String responseMessage;

    public WiselyResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
