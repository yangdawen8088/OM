package com.zero2oneit.mall.member.controller;

import com.zero2oneit.mall.member.service.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zero2oneit.mall.member.service.MemberFeedbackService;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-04-21
 */
@RestController
@RequestMapping("/admin/member/feedback")
public class MemberFeedbackController {

    @Autowired
    private MemberInfoService memberService;


}
