package com.wulekai.cn.how2java;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class TestQuartz {
    public static void main(String[] args) throws Exception{
          //jobDataMap();
       // databaseCurrentJob();
       // exceptionHandle1();
       // exceptionHandle2();
        stop();
    }

    private static void jobDataMap() throws SchedulerException,InterruptedException{
        //创建一个调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //定义一个触发器
        Trigger trigger = newTrigger().withIdentity("trigger1","group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(2)
                        .withRepeatCount(10))
                .build();

        //定义一个JobDetail
        JobDetail job = newJob(MailJob.class)
                .withIdentity("mailjob1","mailgroup")
                .usingJobData("email","admin@10086.com")
                .build();

        job.getJobDataMap().put("email","admin@taobao.com");

        //调度加入这个job
        scheduler.scheduleJob(job,trigger);

        //启动
        scheduler.start();

        Thread.sleep(20000);
        scheduler.shutdown(true);


    }

    private static void databaseCurrentJob() throws Exception{
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        Trigger trigger = newTrigger().withIdentity("trigger1","group1")
                .startNow()
                .withSchedule(simpleSchedule()
                .withIntervalInSeconds(2)
                .withRepeatCount(10))
                .build();

        JobDetail job = newJob(DatabaseBackupJob.class)
                .withIdentity("backupjob","databasegroup")
                .usingJobData("database","db2019")
                .build();

        scheduler.scheduleJob(job,trigger);

        scheduler.start();

        Thread.sleep(20000);
        scheduler.shutdown(true);
    }

    private static void exceptionHandle1() throws Exception{
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        Trigger trigger = newTrigger().withIdentity("trigger1","group1")
                .startNow()
                .withSchedule(simpleSchedule()
                .withIntervalInSeconds(2)
                .withRepeatCount(10))
                .build();

        JobDetail job = newJob(ExceptionJob1.class)
                .withIdentity("exceptionJob1","someJobGroup")
                .build();

        scheduler.scheduleJob(job,trigger);

        scheduler.start();

        Thread.sleep(20000);
        scheduler.shutdown(true);
    }

    private static void exceptionHandle2() throws Exception{
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        Trigger  trigger = newTrigger().withIdentity("trigger1","group1")
                .startNow()
                .withSchedule(simpleSchedule()
                .withIntervalInSeconds(2)
                .withRepeatCount(10))
                .build();

        JobDetail job = newJob(ExceptionJob2.class)
                .withIdentity("exceptionJob1","someIobGroup")
                .build();

        scheduler.scheduleJob(job,trigger);

        scheduler.start();

        Thread.sleep(20000);
        scheduler.shutdown(true);
    }

    private static void stop() throws Exception{
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        Trigger trigger = newTrigger().withIdentity("trigger1","group1")
                .startNow()
                .build();

        JobDetail job = newJob(StoppableJob.class)
                .withIdentity("exceptionJob1","someJobGroup")
                .build();

        scheduler.scheduleJob(job,trigger);

        scheduler.start();

        Thread.sleep(5000);
        System.out.println("过5秒，就会停止调度d");

        scheduler.interrupt(job.getKey());

        Thread.sleep(20000);
        scheduler.shutdown(true);
    }
}
