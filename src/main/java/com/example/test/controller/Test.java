package com.example.test.controller;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class Test {

   public static void main(String[] args) throws IOException {
       File file = new File("F:\\Youku Files\\transcode\\第三.mp4");
       String url = "F:\\Youku Files\\test";
       File file1 = new File(url);
       if (!file1.exists() && !file1.isDirectory()) {
           file1.mkdirs();
       }
       String filename = file.getName();
       String filename1 = "12" + filename.substring(filename.lastIndexOf("."));
       File file2 = new File(url + File.separator + filename1);
       InputStream in = new BufferedInputStream(new FileInputStream(file));
       OutputStream out = new BufferedOutputStream(new FileOutputStream(file2));
       FileInputStream inputStream = new FileInputStream(file);
       MultipartFile multipartFile = new MockMultipartFile(file.getName(), inputStream);
       System.out.println(multipartFile.getName());
       multipartFile.transferTo(new File(url+File.separator+multipartFile.getName()));
       int cl = 0;
       byte buffer[] = new byte[1024];
       long f1 = System.currentTimeMillis();
       System.out.println(f1);
       while ((cl = in.read(buffer)) != -1) {
           out.write(buffer);
       }
       long f2 = System.currentTimeMillis();
       System.out.println(f2);
       System.out.println(f2 - f1);
       in.close();
       out.close();
       inputStream.close();
   }

}
