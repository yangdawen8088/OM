package com.zero2oneit.mall.member.api;

import com.zero2oneit.mall.common.bean.member.MemberFeedback;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.member.service.MemberFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Description:
 *
 * @author yjj
 * @date 2020/7/20 16:34
 */
@RestController
@RequestMapping("/api/auth/member/feedBack")
public class FeedBackApi {

    @Autowired
    private MemberFeedbackService feedbackService;

    /**
     * 意见反馈
     * @param feedback
     * @return
     */
    @PostMapping("/add")
    public R add(@RequestBody MemberFeedback feedback){
        feedbackService.save(feedback);
        return R.ok("意见反馈成功");
    }

}
