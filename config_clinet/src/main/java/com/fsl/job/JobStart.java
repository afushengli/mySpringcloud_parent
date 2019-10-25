package com.fsl.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ScheduledFuture;

/**
 * @Auther: chenrj
 * @Date: 2019/10/25 14:06
 * @Description:
 */


@Slf4j
@RefreshScope
@Component
@Configuration
//@EnableScheduling
public class JobStart {


    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private ScheduledFuture<?> future;


    @Value("${schedules}")
    public  String cron;

    @PostConstruct
    public void init(){

        log.info("首次加载 refreshCron:"+cron);
            stopCron();// 先停止，在开启.
            future = threadPoolTaskScheduler.schedule(new Runnable() {
                @Override
                public void run() {
                    //定时执行的任务
                    startScheduled();

                }
            }, new CronTrigger(cron));
    }








    //@Scheduled(cron="${schedules}")// 每天中午12点触发
    public void startScheduled() {
        log.info("1111");
    }


    /**
     * 停止定时任务
     */
    public void stopCron() {
        if (future != null) {
            future.cancel(true);
        }
        log.info("DynamicTask.stopCron()");
    }



}
