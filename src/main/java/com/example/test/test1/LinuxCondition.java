package com.example.test.test1;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @ProjectName: test
 * @Package: com.example.test.test1
 * @ClassName: LinuxCondition
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/01 9:27
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/01 9:27
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class LinuxCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return context.getEnvironment().getProperty("os.name").contains("Linux");
    }
}
