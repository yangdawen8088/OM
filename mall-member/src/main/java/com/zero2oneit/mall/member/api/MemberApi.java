package com.zero2oneit.mall.member.api;

import com.zero2oneit.mall.common.bean.auth.WxAppletsAuth;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.member.service.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Description: 用户个人信息操作
 *
 * @author yjj
 * @date 2021/3/5 18:18
 */
@RestController
@RequestMapping("/api/auth/member/info")
public class MemberApi {

    @Autowired
    MemberInfoService memberInfoService;

    /**
     * 修改个人信息
     * @param params 参数
     * @return
     */
    @PostMapping("/edit")
    public R edit(@RequestBody Map<String, String> params){
        return memberInfoService.edit(params);
    }

}
