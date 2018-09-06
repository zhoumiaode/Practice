package com.example.test.test1;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ProjectName: test
 * @Package: com.example.test.test1
 * @ClassName: ScheduledTaskService
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/31 17:25
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/31 17:25
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Service
public class ScheduledTaskService {

    private static final SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm:ss");

    //@Scheduled(fixedRate = 5000)
    public void reportCurrentTime(){
        System.out.println("每隔5秒执行一次"+dateFormat.format(new Date()));
    }

    //@Scheduled(cron = "*/10 * * * * *")
    public void fixTimeExcetion(){
        System.out.println("指定时间执行"+dateFormat.format(new Date()));
    }

    //@Scheduled(fixedRate = 3000)
    public String f(){
        System.out.println("消息推送");
        return "redirect:/welcome";
    }
}
