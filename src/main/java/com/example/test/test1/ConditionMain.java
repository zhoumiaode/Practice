package com.example.test.test1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ProjectName: test
 * @Package: com.example.test.test1
 * @ClassName: ConditionMain
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/01 9:32
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/01 9:32
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class ConditionMain {

    public static void main(String[] args){
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(ConditionConfig.class);
        ListService listService=context.getBean(ListService.class);
        System.out.println(context.getEnvironment().getProperty("os.name")+"系统下的列表命令为："+listService.showListCmd());
        context.close();
    }
}
