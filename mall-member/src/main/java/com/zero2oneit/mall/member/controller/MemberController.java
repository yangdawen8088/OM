package com.zero2oneit.mall.member.controller;

import com.zero2oneit.mall.common.query.member.InfoQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.member.service.MemberAddressService;
import com.zero2oneit.mall.member.service.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-04-21
 */
@RestController
@RequestMapping("/admin/member")
public class MemberController {

    @Autowired
    private MemberInfoService memberService;

    /**
     * 查询会员信息列表
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid list(@RequestBody InfoQueryObject qo) {
        return memberService.pageList(qo);
    }

    /**
     * 重置会员登录密码
     * @param memberId
     * @return
     */
    @PostMapping("/resetPwd")
    public R resetPwd(@RequestParam("memberId") String memberId){
        return memberService.resetPwd(memberId);
    }

}
