package com.example.test.service;

import com.example.test.domain.Girl;
import com.example.test.enums.ResultEnums;
import com.example.test.exception.GirlException;
import com.example.test.mapper.GirlMapper;
import com.example.test.repository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

    @Resource
    private GirlMapper girlMapper;

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
        Girl girl=null;
        if(girlRepository.findById(id).isPresent()) {
            girl = girlRepository.findById(id).get();
        }
        return girl;

    }

    public boolean findById(Integer id){
        return girlRepository.existsById(id);
    }

    public void deleteById(Integer id){
        girlRepository.deleteById(id);
    }

    public Optional<Girl> findOne1(Integer id){
        return girlRepository.findById(id);

    }

    public Girl findAB(String name,int age){
        return girlRepository.findAB(name,age);

    }

    @Transactional
    public int updateAB(String name,int age){
        return girlRepository.updateAB(name,age);

    }

    public Page<Girl> findAll(Pageable pageable){
        return girlRepository.findAll(pageable);
    }

    public List<Girl> findAllByMybatis() throws Exception {
        return girlMapper.findAll();
    }

    public Girl findByIdMyBatis(Integer id){
        return girlMapper.findById(id);
    }
}
