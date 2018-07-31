package com.example.test.repository;

import com.example.test.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @ProjectName: test
 * @Package: com.example.test.repository
 * @ClassName: AddressRepository
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/31 10:17
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/31 10:17
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public interface AddressRepository extends JpaRepository<Address,Integer> {

    @Query("select a from Address a where a.id=1")
    Address findA();
}
