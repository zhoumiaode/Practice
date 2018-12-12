package com.example.test.repository;

import com.example.test.domain.Girl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface GirlRepository extends JpaRepository<Girl,Integer> {
    //通过年龄查询
    public List<Girl> findByName(String name);

    @Query(value = "select a from Girl a where a.name=?1 and a.age=?2")
     public Girl findAB(String name,int age);

    @Modifying
    @Query(value = "update Girl a set a.age=:age where a.name=:name")
    public int updateAB(@Param("name") String name,@Param("age") int age);

    @Query(value = "select t from Girl t")
    public Page<Girl> findAll(Pageable pageable);
}
