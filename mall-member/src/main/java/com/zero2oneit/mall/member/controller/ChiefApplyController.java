package com.zero2oneit.mall.member.controller;

import com.zero2oneit.mall.common.bean.member.ChiefApply;
import com.zero2oneit.mall.common.query.member.ChiefManageQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.member.service.ChiefApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-04-27
 */
@RestController
@RequestMapping("/admin/chiefApplyManage")
public class ChiefApplyController {

    @Autowired
    private ChiefApplyService chiefApplyService;

    /**
     * 分页
     * @param qo
     * @return
     */
    @PostMapping("/chiefList")
    private BoostrapDataGrid chiefList(@RequestBody ChiefManageQueryObject qo){
        return chiefApplyService.chiefList(qo);
    }

    /**
     * 站点开关
     * @param status
     * @return
     */
    @PostMapping("/chiefStatusEdit")
    private R chiefStatusEdit(@RequestParam("status") String status, @RequestParam("id") String id){
        return chiefApplyService.chiefStatusEdit(status,id);
    }

    /**
     * 驿站审核申请
     * @param chiefApplies
     * @return
     */
    @PostMapping("/chiefByIds")
    public R chiefByIds(@RequestBody Object chiefApplies){
        return chiefApplyService.chiefByIds(chiefApplies);
    }

    /**
     * 站点添加
     * @param chiefApply
     * @return
     */
    @PostMapping("/addOrEdit")
    public R addOrEdit(@RequestBody ChiefApply chiefApply){
        return chiefApplyService.addOrEdit(chiefApply);
    }

}
