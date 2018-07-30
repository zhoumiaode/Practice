package com.example.test.controller;

import com.example.test.domain.Girls;
import com.example.test.domain.Persons;
import com.example.test.service.GirlsService;
import com.example.test.service.PersonsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ProjectName: test
 * @Package: com.example.test.controller
 * @ClassName: GirlsController
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/30 14:33
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/30 14:33
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@RestController
public class GirlsController {

    @Resource
    private GirlsService girlsService;
    @Resource
    private PersonsService personsService;

    @GetMapping(value = "abc")
    public List<Girls> find(){

        int id=1;
        return girlsService.find(id );
    }

    @GetMapping(value = "abcd")
    public List<Persons> find1(){

        int age=1;
        return personsService.find(age);
    }
}
