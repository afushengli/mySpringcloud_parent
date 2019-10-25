package com.fsl.Controller;

import com.fsl.job.JobStart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ScheduledFuture;

/**
 * @Auther: chenrj
 * @Date: 2019/10/25 14:37
 * @Description:
 */
@RefreshScope
@RestController
@Slf4j
public class TestController {

    private ScheduledFuture<?> future;


    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;



    @GetMapping("/reresh")
    public String reresh(){

        stopCron();

        log.info("刷新的定时时间 refreshCron:"+cron);
        future = threadPoolTaskScheduler.schedule(new Runnable() {
            @Override
            public void run() {
                log.info("12121212121");
            }
        }, new CronTrigger(cron));

        return null;

    }


    public void stopCron() {
        if (future != null) {
            future.cancel(true);
        }
        log.info("DynamicTask.stopCron()");
    }






    @Value("${schedules}")
    private String cron;

    @GetMapping("/cron")
    public String param(){
        return cron;
    }
}
