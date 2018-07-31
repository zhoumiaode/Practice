package com.example.test.service;

import com.example.test.domain.People;
import com.example.test.repository.PeopleRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ProjectName: test
 * @Package: com.example.test.service
 * @ClassName: PeopleService
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/31 10:52
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/31 10:52
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Service
public class PeopleService {

    @Resource
    private PeopleRepository peopleRepository;

    public People find(){
        People people=peopleRepository.find();
        return people;
    }
}
