package com.wulekai.cn.how2java;

import org.quartz.*;

@DisallowConcurrentExecution
public class DatabaseBackupJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        String database = jobDetail.getJobDataMap().getString("database");
        System.out.printf("给数据库 %s 备份,耗时10秒 %n",database);
        try {
            Thread.sleep(10000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
