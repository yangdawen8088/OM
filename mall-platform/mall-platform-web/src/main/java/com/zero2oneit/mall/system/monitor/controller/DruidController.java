package com.zero2oneit.mall.system.monitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * druid 监控
 */
@Controller
@RequestMapping("/monitor/index")
public class DruidController {

    @GetMapping()
    public String index(){
        return "redirect:/druid/index";
    }

}
