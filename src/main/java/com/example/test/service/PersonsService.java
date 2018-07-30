package com.example.test.service;

import com.example.test.domain.Persons;
import com.example.test.mapper.PersonsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ProjectName: test
 * @Package: com.example.test.service
 * @ClassName: PersonsService
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/30 14:38
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/30 14:38
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Service
public class PersonsService {

    @Resource
    private PersonsMapper personsMapper;

    public List<Persons> find(int age){

        return personsMapper.findByAge(age);
    }

}
