package com.zero2oneit.mall.member.api;

import com.zero2oneit.mall.common.bean.member.ChiefApply;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.member.service.ChiefApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author lf
 * @date 2021/4/29 9:25
 */
@RestController
@RequestMapping("/api/auth/member/chief")
public class ChiefApi {

    @Autowired
    private ChiefApplyService chiefApplyService;

    /**
     * 站点申请
     * @param
     * @return
     */
    @PostMapping("/add")
    public R add(@RequestBody ChiefApply chiefApply){
        chiefApplyService.save(chiefApply);
        return R.ok("站点申请成功");
    }



}
