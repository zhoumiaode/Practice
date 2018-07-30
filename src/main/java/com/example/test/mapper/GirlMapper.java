package com.example.test.mapper;

import com.example.test.domain.Girl;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @ProjectName: test
 * @Package: com.example.test.mapper
 * @ClassName: GirlMapper
 * @Description: MyBatis数据库访问语句的注解形式
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/30 11:19
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/30 11:19
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public interface GirlMapper {

    @Select("select * from girl")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property ="name" ,column = "name"),
            @Result(property ="cupSize" ,column = "cup_size"),
            @Result(property ="age" ,column = "age"),
            @Result(property ="persons" ,column = "age",many = @Many(select = "com.example.test.mapper.PersonsMapper.findByAge"))
    })
    public List<Girl> findAll()throws Exception;



    @Select("select * from girl where id=#{id}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property ="name" ,column = "name"),
            @Result(property ="cupSize" ,column = "cup_size"),
            @Result(property ="age" ,column = "age")
    })
    public Girl findById(@Param("id") Integer id);
}
