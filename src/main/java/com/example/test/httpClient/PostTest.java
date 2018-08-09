package com.example.test.httpClient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @ProjectName: test
 * @Package: com.example.test.httpClient
 * @ClassName: PostTest
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/08 14:41
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/08 14:41
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class PostTest {

    public static String post(String url,String param) throws IOException {
        String str="";
        try {
            URL urls = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urls.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("token","123");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            BufferedWriter out = null;
            BufferedReader in = null;
            if (param != null &&!param.equals("")){
                 out = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
                 out.write(param);
                 out.close();
           }
              in=new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            String a;
            while((a=in.readLine())!=null){
                str+=a;

            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }finally {
            return str;
        }
    }
}
