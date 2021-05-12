package com.zero2oneit.mall.system.notice.controller;

import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.system.notice.query.NoticeQueryObject;
import com.zero2oneit.mall.system.notice.service.NoticeService;
import com.zero2oneit.mall.system.notice.vo.NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping("/noticePage")
    @ResponseBody
    public BoostrapDataGrid noticePage(@RequestBody NoticeQueryObject qo) {//VO : value-object 值对象
        return noticeService.queryList(qo);
    }

    @RequestMapping("/addOrEditNotice")
    @ResponseBody
    public R addOrEditNotice(NoticeVO vo) {//VO : value-object 值对象
        noticeService.addOrEditNotice(vo);
        return R.ok("操作成功");
    }

    @RequestMapping("/changeNoticeStatus")
    @ResponseBody
    public R changeNoticeStatus(String status, String ids) {
        noticeService.changeNoticeStatus(status, ids);
        return R.ok("改变属性状态成功");
    }

}
