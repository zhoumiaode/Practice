package com.example.test.zhifubao;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.*;

/**
 * @ProjectName: test
 * @Package: com.example.test.zhifubao
 * @ClassName: AliPayController
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/10 14:11
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/10 14:11
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@RestController
public class AliPayController {


    @PostMapping(value = "payss",produces = {"application/json;charset=UTF-8"})
    public String pay() throws Exception {
        /*produces = {"application/json;charset=UTF-8"}*/
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        String ac="你好啊";
        //ac = new String(ac.getBytes("ISO-8859-1"), "utf-8");
        model.setBody(ac);
        model.setSubject("你弟弟和啊");
        model.setOutTradeNo("781");
        model.setTimeoutExpress("30m");
        model.setTotalAmount("12");
        model.setProductCode("QUICK_MSECURITY_PAY");
        //不确定调用sdkExecute和Execute是否需要在实例化的时候调用不同的公钥
        AlipayClient alipayClient = new DefaultAlipayClient(AliPayConfig.gatewayUrl,AliPayConfig.app_id,AliPayConfig.private_key,AliPayConfig.formate,AliPayConfig.charset,AliPayConfig.ali_public_key,AliPayConfig.signType);
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        request.setBizModel(model);
        request.setNotifyUrl(AliPayConfig.notifyUrl);
        request.setReturnUrl(AliPayConfig.returnUrl);
        String orderStr="";
        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
        Map<String,String> m=response.getParams();
        orderStr = response.getBody();
        String deurl = URLDecoder.decode(orderStr,"UTF-8");
        //orderStr = orderStr.replaceAll("\\+","%20");
        String[] a=orderStr.split("&");
        //orderStr = new String(orderStr.getBytes("ISO-8859-1"), "utf-8");
        return orderStr;
    }

    @PostMapping(value = "notify", produces = "text/html;charset=UTF-8")
    public String  notify(HttpServletRequest requests, HttpServletResponse httpServletResponse) throws Exception {
        //httpServletResponse.getWriter().print("success");
        System.out.println("11111111111");
        Map requestParams = requests.getParameterMap();
        System.out.println("支付宝支付结果通知"+requestParams.toString());
        //获取支付宝POST过来反馈信息
        SortedMap<String,String> params = new TreeMap<String,String>();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。

            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        try {
         //验证签名
            boolean flag = AlipaySignature.rsaCheckV1(params, AliPayConfig.ali_public_key, AliPayConfig.charset, AliPayConfig.signType);
            if(flag){
                if("TRADE_SUCCESS".equals(params.get("trade_status"))){
                    //付款金额
                    String amount = params.get("buyer_pay_amount");
                    //商户订单号
                    String out_trade_no = params.get("out_trade_no");
                    //支付宝交易号
                    String trade_no = params.get("trade_no");
                    System.out.println("返回码成功");
                    //附加数据
                   // String passback_params = URLDecoder.decode(params.get("passback_params"));

                }else{
                    System.out.println("返回码失败");
                }
            }else{
                System.out.println("验签失败");
            }
        } catch (AlipayApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "success";
    }
}
