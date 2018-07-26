package com.example.test.service;

import com.example.test.domain.Girl;
import com.example.test.domain.Man;
import com.example.test.enums.ResultEnums;
import com.example.test.exception.GirlException;
import com.example.test.repository.GirlRepository;
import com.example.test.repository.ManRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ProjectName: test
 * @Package: com.example.test.service
 * @ClassName: ManService
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/26 10:09
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/26 10:09
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Service
public class ManService {
    @Autowired
    private ManRepository manRepository;

    public Man findOne(Integer id){
        return manRepository.findById(id).get();

    }
}
