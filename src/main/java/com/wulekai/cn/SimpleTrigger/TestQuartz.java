package com.wulekai.cn.SimpleTrigger;

import org.quartz.DateBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class TestQuartz {
    public static void main(String[] args) throws Exception{
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //下个10秒执行
      //  Date startTime = DateBuilder.futureDate(10,DateBuilder.IntervalUnit.SECOND);

        Date startTime = DateBuilder.nextGivenSecondDate(null, 8);

        JobDetail job =newJob(MailJob.class).withIdentity("mailJob","mailGroup").build();

        SimpleTrigger simpleTrigger =(SimpleTrigger) newTrigger().withIdentity("trigger1","group1")
                .startAt(startTime).
                        withSchedule(simpleSchedule().repeatForever().withIntervalInSeconds(3)).build();

        Date date = scheduler.scheduleJob(job, simpleTrigger);

        System.out.println("当前时间是："+new Date().toLocaleString());

//        System.out.printf("%s 这个任务会在 %s 准时开始运行，累计运行%d次，间隔时间是%d毫秒%n",job.getKey(),
//                date.toLocaleString(),simpleTrigger.getRepeatCount()+1,simpleTrigger.getRepeatInterval());

        System.out.printf("%s 这个任务会在 %s 准时开始运行，累计运行%d次，间隔时间是%d毫秒%n", job.getKey(), date.toLocaleString(), simpleTrigger.getRepeatCount()+1, simpleTrigger.getRepeatInterval());
        scheduler.start();

        Thread.sleep(20000);
        scheduler.shutdown(true);
    }
}
