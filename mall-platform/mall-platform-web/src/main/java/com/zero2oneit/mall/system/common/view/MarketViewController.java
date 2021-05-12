package com.zero2oneit.mall.system.common.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 视图跳转统一处理
 */
@Controller
public class MarketViewController {

/*
    //广告管理
    @RequestMapping("/advert.html")
    public String toAdvert() {
        return "service/market/advert";
    }
*/


    //上传测试
    @RequestMapping("/lottery.html")
    public String toAlottery() {
        return "service/market/lottery";
    }

}
