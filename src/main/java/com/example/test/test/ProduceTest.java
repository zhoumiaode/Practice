package com.example.test.test;

/**
 * @ProjectName: test
 * @Package: com.example.test.test
 * @ClassName: ProduceTest
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/24 14:15
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/24 14:15
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class ProduceTest {

    public static void main(String[] args){

        AbstractRepository repository=new Repository();
        Produce produce1=new Produce(repository);
        Produce produce2=new Produce(repository);
        Produce produce3=new Produce(repository);
        Produce produce4=new Produce(repository);
        Produce produce5=new Produce(repository);
        Produce produce6=new Produce(repository);
        Customer customer1=new Customer(repository);
        Customer customer2=new Customer(repository);
        Customer customer3=new Customer(repository);
        produce1.setNum(100);
        produce2.setNum(100);

        //produce2.setPriority(1);
        produce3.setNum(100);
        produce4.setNum(100);
        produce5.setNum(200);
        produce6.setNum(200);
        customer1.setNum(200);
        customer2.setNum(200);
        customer3.setNum(200);

        customer1.start();
        customer2.start();
        customer3.start();
        produce1.start();
        produce2.start();
        produce3.start();
        produce4.start();
        produce5.start();
        produce6.start();
    }
}
