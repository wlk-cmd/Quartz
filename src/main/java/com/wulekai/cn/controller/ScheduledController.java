package com.wulekai.cn.controller;

import com.wulekai.cn.service.ScheduledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduledController {

    @Autowired
    ScheduledService scheduledService;

    @GetMapping("/time")
    public String time(){
        scheduledService.hello();
        return "time success";
    }
}
