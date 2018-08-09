package com.example.test.httpClient;

import java.io.*;

/**
 * @ProjectName: test
 * @Package: com.example.test.httpClient
 * @ClassName: FileTest1
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/09 9:17
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/09 9:17
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class FileTest1 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("F:\\12.txt");
        byte[] bbuf = new byte[1024];
        int hasRead = 0;
        StringBuffer sb = new StringBuffer("");
        while((hasRead = fileInputStream.read(bbuf)) != -1){
            sb.append(new String(bbuf, 0, hasRead, "utf-8"));
        }
        fileInputStream.close();
        System.out.println(sb);
    }
}
