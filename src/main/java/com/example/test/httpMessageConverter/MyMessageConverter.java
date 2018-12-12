package com.example.test.httpMessageConverter;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;

/**
 * @ProjectName: test
 *
 *
 * @Package: com.example.test.httpMessageConverter
 * @ClassName: MyMessageConverter
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/06 14:09
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/06 14:09
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class MyMessageConverter extends AbstractHttpMessageConverter<DemoObj> {
    @Override
    protected boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    protected DemoObj readInternal(Class<? extends DemoObj> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(DemoObj demoObj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }
}
