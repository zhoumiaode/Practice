package com.example.test.httpClient;

import java.io.*;

/**
 * @ProjectName: test
 * @Package: com.example.test.httpClient
 * @ClassName: FileTest
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/09 9:11
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/09 9:11
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class FileTest {

    public static void main(String[] args){

        try {
            FileInputStream in=new FileInputStream("F:\\12.txt");
            BufferedReader b=new BufferedReader(new InputStreamReader(in,"GBK"));
            String a;
            String c = "";
            while ((a=b.readLine())!=null){
                c+=a;
            }
            System.out.println(c);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
