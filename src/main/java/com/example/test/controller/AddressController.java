package com.example.test.controller;

import com.example.test.domain.Address;
import com.example.test.domain.People;
import com.example.test.service.AddressService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: test
 * @Package: com.example.test.controller
 * @ClassName: AddressController
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/31 10:20
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/31 10:20
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@RestController
public class AddressController {

    @Resource
    private AddressService addressService;

    @GetMapping(value = "findA")
    public Address findA(){
        return addressService.findA();
    }

    @PostMapping(value = "addA")
    public Address saveOne(){

        Address address=new Address();
        address.setAddress("1");
        address.setPhone("1");
        List<People> peoples=new ArrayList<People>();
        People people=new People();
        people.setId(1);
        people.setName("1");
        people.setSex("1");
        people.setAddress(address);
        People people1=new People();
        people1.setId(2);
        people1.setName("2");
        people1.setSex("2");
        people1.setAddress(address);
        peoples.add(people);
        peoples.add(people1);
 /*       address.setPeoples(peoples);*/
        addressService.addA(address);
        return address;
    }
}
