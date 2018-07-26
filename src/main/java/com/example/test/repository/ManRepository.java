package com.example.test.repository;

import com.example.test.domain.Man;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ProjectName: test
 * @Package: com.example.test.repository
 * @ClassName: ManRepository
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/26 10:08
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/26 10:08
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public interface ManRepository extends JpaRepository<Man,Integer> {
}
