package com.example.test.mapper;

import com.example.test.domain.Girls;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @ProjectName: test
 * @Package: com.example.test.mapper
 * @ClassName: GilrsMapper
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/30 14:27
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/30 14:27
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public interface GilrsMapper {


    @Select("select * from girls where id =#{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age"),
            @Result(property = "persons", column = "id", many = @Many(select = "com.example.test.mapper.PersonsMapper.findByAge"))
    })
    public List<Girls> findAll( int id);

    @Insert("insert into girls(id,name,age) values(#{id},#{name},#{age})")
    public void saveGilrs(Girls girls);

    @Select("select * from girls where id =#{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age")
    })
    public Girls findById( int id);
}
