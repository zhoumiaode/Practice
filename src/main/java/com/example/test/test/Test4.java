package com.example.test.test;

import com.example.test.domain.Girl;

/**
 * @ProjectName: test
 * @Package: com.example.test.test
 * @ClassName: Test4
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/27 13:58
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/27 13:58
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class Test4 {

    public static void main(String[] args){

        Girl girl=new Girl();
        if(girl!=null){
            System.out.println(1);
        }else if(girl==null){
            System.out.println(0);
        }
    }
}
