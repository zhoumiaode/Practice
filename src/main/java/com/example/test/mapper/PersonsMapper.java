package com.example.test.mapper;

import com.example.test.domain.Persons;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ProjectName: test
 * @Package: com.example.test.mapper
 * @ClassName: PersonsMapper
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/30 13:55
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/30 13:55
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public interface PersonsMapper {



    @Select(value = "select * from persons where age=#{age}")
    @Results({
           @Result(property = "id",column = "id"),
           @Result(property = "name",column = "name"),
           @Result(property = "age",column = "age"),
    })
    public List<Persons> findByAge(int age);
}
