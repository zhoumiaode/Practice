package com.example.test.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @ProjectName: test
 * @Package: com.example.test.controller
 * @ClassName: MultipartFileController
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/03 14:18
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/03 14:18
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class MultipartFileController {

    @RequestMapping(value = "upload")
    public String upload(MultipartFile file) {
        try {
            FileUtils.writeByteArrayToFile(new File("e:/upload/" + file.getOriginalFilename()), file.getBytes());
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
