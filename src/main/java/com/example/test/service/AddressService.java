package com.example.test.service;

import com.example.test.domain.Address;
import com.example.test.repository.AddressRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ProjectName: test
 * @Package: com.example.test.service
 * @ClassName: AddressService
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/31 10:19
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/31 10:19
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Service
public class AddressService {

    @Resource
    private AddressRepository addressRepository;

    public Address findA(){
        return addressRepository.findA();
    }

    public void addA(Address address){
        addressRepository.save(address);
    }
}
