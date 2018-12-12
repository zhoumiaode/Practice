package com.example.test.httpClient;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @ProjectName: test
 * @Package: com.example.test.httpClient
 * @ClassName: PppController
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/08 15:02
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/08 15:02
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Controller
public class PppController {

    @ResponseBody
    @PostMapping(value = "ppp")
    public Object getPost(HttpServletRequest req, HttpServletResponse res){
        try {
            InputStream inputStream;
            StringBuffer sb = new StringBuffer();
            inputStream = req.getInputStream();
            String s;
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((s = in.readLine()) != null)
            {
                sb.append(s);
            }
            in.close();
            inputStream.close();
            System.out.println(sb.toString());
            BufferedOutputStream out = new BufferedOutputStream(res.getOutputStream());
            String a="123";
            out.write(a.getBytes());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "String";

    }
}
