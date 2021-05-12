package com.zero2oneit.mall.member.service.impl;

import com.zero2oneit.mall.common.bean.member.MemberFeedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zero2oneit.mall.member.mapper.MemberFeedbackMapper;
import com.zero2oneit.mall.member.service.MemberFeedbackService;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-04-21
 */
@Service
public class MemberFeedbackServiceImpl extends ServiceImpl<MemberFeedbackMapper, MemberFeedback> implements MemberFeedbackService {

    @Autowired
    private MemberFeedbackMapper memberFeedbackMapper;

}