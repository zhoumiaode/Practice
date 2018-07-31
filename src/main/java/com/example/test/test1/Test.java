package com.example.test.test1;

import java.io.*;
import java.nio.Buffer;

/**
 * @ProjectName: test
 * @Package: com.example.test.test1
 * @ClassName: Test
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/31 15:25
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/31 15:25
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class Test {

    public static void main(String[] args) throws IOException {

        File file=new File("F:\\Youku Files\\transcode\\第四.mp4");
        String url="F:\\Youku Files\\test";
        String filename=file.getName();
        File file1=new File(url);
        if(!file1.exists()){
            file1.mkdirs();
        }

        url=url+File.separator+filename;
        File file2=new File(url);
        /*InputStream in=new BufferedInputStream(new FileInputStream(file));
        OutputStream out=new BufferedOutputStream(new FileOutputStream(file2));*/
        InputStream in=new FileInputStream(file);
        OutputStream out=new FileOutputStream(file2);
        byte buffer[]=new byte[1024];
        int cl=0;
        while((cl=in.read(buffer))!=-1){ //通过流形式进行上传文件
            out.write(buffer);
        }
        out.flush();//刷新缓冲区
        in.close();out.close();

    }
}
