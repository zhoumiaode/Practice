package com.example.test.controller;

import com.example.test.domain.Address;
import com.example.test.domain.People;
import com.example.test.service.PeopleService;
import com.example.test.service.PersonsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: test
 * @Package: com.example.test.controller
 * @ClassName: PeopleController
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/31 10:55
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/31 10:55
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@RestController
public class PeopleController {

    @Resource
    private PeopleService peopleService;

    /** 
    * @Description:  在一对多以及多对一的对象显示时，只能显示一方的内容，如果显示了一这边，那么在多这边要自己封装多对一中的一对象
    * @Param: []
    * @return: java.lang.Object 
    * @Author: zhoumiaode
    * @Date: 2018/07/31 
    */ 
    @GetMapping(value = "findAA")
    public Object find(){

        People people=peopleService.find();
        System.out.println(people.getAddress().getId());
        people.setAddress(people.getAddress());
        List<Address> li=new ArrayList<Address>();
        li.add(people.getAddress());
        Map<Object,Object> map=new HashMap<Object,Object>();

        map.put("people",people);
        map.put("li",li);
        return people;
    }
}
