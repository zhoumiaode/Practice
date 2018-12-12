package com.example.test.repository;

import com.example.test.domain.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @ProjectName: test
 * @Package: com.example.test.repository
 * @ClassName: PeopleRepository
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/31 10:54
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/31 10:54
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public interface PeopleRepository extends JpaRepository<People,Integer>{
    @Query("select a from People a where a.id=1")
    People find();
}
