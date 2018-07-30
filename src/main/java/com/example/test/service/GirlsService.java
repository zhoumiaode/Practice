package com.example.test.service;

import com.example.test.domain.Girls;
import com.example.test.mapper.GilrsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ProjectName: test
 * @Package: com.example.test.service
 * @ClassName: GirlsService
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/30 14:31
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/30 14:31
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Service
public class GirlsService {

    @Resource
    private GilrsMapper gilrsMapper;

    public List<Girls> find(int id){

        return gilrsMapper.findAll(id);
    }
}
