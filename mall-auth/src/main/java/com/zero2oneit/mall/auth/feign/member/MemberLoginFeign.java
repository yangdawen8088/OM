package com.zero2oneit.mall.auth.feign.member;

import com.zero2oneit.mall.common.bean.auth.AppWxLoginObject;
import com.zero2oneit.mall.common.bean.auth.RegisterInfo;
import com.zero2oneit.mall.common.bean.auth.WxAppletsAuth;
import com.zero2oneit.mall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description:
 *
 * @author yjj
 * @date 2021/3/5 18:03
 */
@FeignClient("member-service")
public interface MemberLoginFeign {

    @PostMapping("/member/login/WxAppletsLogin")
    R WxAppletsLogin(@RequestBody WxAppletsAuth wxAppletsAuth);


    @RequestMapping("/login/memberApp/wxauth")
    public R wxauth(@RequestBody AppWxLoginObject appWxLoginObject);

    @RequestMapping("/login/memberApp/appCodePhone")
    public R appCodePhone(@RequestBody AppWxLoginObject appWxLoginObject);

    @RequestMapping("/login/memberApp/appPassPhone")
    public R appPassPhone(@RequestBody AppWxLoginObject appWxLoginObject);

    @RequestMapping("/login/memberApp/bindWx")
    public R bindWx(@RequestBody AppWxLoginObject appWxLoginObject);


    @RequestMapping("/login/memberApp/checkPhone")
    public Boolean checkPhone(@RequestBody String phone);

    @RequestMapping("/login/memberApp/register")
    public R register(@RequestBody AppWxLoginObject appWxLoginObject);

    @RequestMapping("/login/memberApp/registerHtml")
    public R registerHtml(@RequestBody RegisterInfo registerInfo);

    @RequestMapping("/login/memberApp/appUpdatePass")
    R appUpdatePass(@RequestBody AppWxLoginObject appWxLoginObject);


    @PostMapping("/login/memberApp/appeltWxauth")
    public R appeltWxauth(@RequestBody AppWxLoginObject appWxLoginObject);

}
