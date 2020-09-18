package com.wulekai.cn.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Async
public class AsyncService {

    public void hello(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("业务进行中");
    }
}
