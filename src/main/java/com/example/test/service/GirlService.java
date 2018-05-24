package com.example.test.service;

import com.example.test.domain.Girl;
import com.example.test.enums.ResultEnums;
import com.example.test.exception.GirlException;
import com.example.test.repository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

    @Transactional
    public void insertTwo(){
        Girl girlA=new Girl();
        girlA.setId(5);
        girlA.setCupSize("4");
        girlA.setName("4");
        Girl girlB=new Girl();
        girlB.setId(6);
        girlB.setCupSize("555555");
        girlB.setName("5");
        girlRepository.save(girlA);
        girlRepository.save(girlB);
    }

    public void  getAge(Integer id) throws Exception {
        Girl girl=girlRepository.findById(id).get();
        Integer age=girl.getAge();
        if(age<10){
            throw new GirlException(ResultEnums.PRIMARY);
        }else if(age>10&&age<16){
            throw new GirlException(ResultEnums.MIDDLE_SCHOOL);
        }
    }

    public Girl findOne(Integer id){
        return girlRepository.findById(id).get();

    }
}
