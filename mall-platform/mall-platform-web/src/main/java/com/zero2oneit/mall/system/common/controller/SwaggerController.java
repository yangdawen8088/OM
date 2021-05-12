package com.zero2oneit.mall.system.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * swagger 接口
 */
@Controller
@RequestMapping("/tool/swagger")
public class SwaggerController {

    @GetMapping()
    public String index() {
        return "redirect:/swagger-ui.html";
    }

}
