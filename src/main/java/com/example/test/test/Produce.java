package com.example.test.test;

/**
 * @ProjectName: test
 * @Package: com.example.test.test
 * @ClassName: Produce
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/24 14:09
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/24 14:09
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class Produce extends Thread {
    private int num;
    private AbstractRepository abstractRepository;

    public Produce(AbstractRepository abstractRepository) {
        this.abstractRepository = abstractRepository;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        produce(num);
    }
    // 调用仓库Storage的生产函数
     public void produce(int num)
     {
         abstractRepository.produce(num);
     }
}
