package com.example.test;

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
}
