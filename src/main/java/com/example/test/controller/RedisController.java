package com.example.test.controller;

import com.example.test.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: test
 * @Package: com.example.test
 * @ClassName: RedisController
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/01 13:49
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/01 13:49
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@RestController
@PropertySource("classpath:redis.properties")
public class RedisController {


    @Resource
    private RedisUtil redisUtil;
    @Resource
    private RedisTemplate<Object,Object> redisTemplate;

    @Value(value = "${aname}")
    private String aname;



    @PostMapping(value = "saveRedis")
    public Object savaString(){
        boolean flag=redisUtil.hasKey("abcd");
        redisUtil.set("abcd","abcd");
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("1","1");
        map.put("2","2");
        redisUtil.hmset("map",map);
        Map<Object,Object> maps=new HashMap<Object,Object>();
        maps=redisUtil.hmget("map");
        for(Map.Entry<Object,Object> mapss:maps.entrySet()){
            System.out.println("key:"+mapss.getKey()+"|value："+mapss.getValue());
        }
        System.out.println(aname);
        return flag;
    }

    @GetMapping(value = "getRedis")
    public Object getString(){

        Object object=null;
        object= redisUtil.get("a");
        return object;
    }

    @GetMapping(value = "aa")
    public Object getaString(){
        boolean flag=false;
        if(redisUtil.hasKey("user::6")){
            flag=true;
        }
        return  flag;
    }
}
