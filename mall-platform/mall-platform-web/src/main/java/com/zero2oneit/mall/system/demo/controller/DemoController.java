package com.zero2oneit.mall.system.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {

    @RequestMapping("/demo")
    public String test() {
        //System.out.println("5555555666"+Global.getName());
        return "common/building";
    }

}
