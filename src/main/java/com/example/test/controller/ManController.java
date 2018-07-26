package com.example.test.controller;

import com.example.test.domain.Man;
import com.example.test.service.ManService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ProjectName: test
 * @Package: com.example.test.controller
 * @ClassName: ManController
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/26 10:10
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/26 10:10
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@RestController
public class ManController {

    @Resource
    private ManService manService;

    @GetMapping(value="get/{id}")
    public Man getById(@PathVariable("id") Integer id){

        return manService.findOne(id);

    }
}
