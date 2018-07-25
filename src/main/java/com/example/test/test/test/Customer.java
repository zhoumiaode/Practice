package com.example.test.test.test;

import com.example.test.test.AbstractRepository;

/**
 * @ProjectName: test
 * @Package: com.example.test.test
 * @ClassName: Customer
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/24 14:14
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/24 14:14
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class Customer extends Thread {

    private int num;

    private com.example.test.test.test.AbstractRepository abstractRepository;

    public Customer(com.example.test.test.test.AbstractRepository abstractRepository) {
        this.abstractRepository = abstractRepository;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        consume(num);

    }
    // 调用仓库Storage的生产函数
     public void consume(int num)
     {
         abstractRepository.pop(num);
     }
}
