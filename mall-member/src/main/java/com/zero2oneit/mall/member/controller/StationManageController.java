package com.zero2oneit.mall.member.controller;

import com.zero2oneit.mall.common.annotion.OperateLog;
import com.zero2oneit.mall.common.bean.member.StationManage;
import com.zero2oneit.mall.common.enums.BusinessType;
import com.zero2oneit.mall.common.query.member.StationManageQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.tree.BaseTreeNode;
import com.zero2oneit.mall.member.service.StationManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description:
 *
 * @author yjj
 * @date 2021/3/1 10:51
 */
@RestController
@RequestMapping("/admin/stationManage")
public class StationManageController {

    @Autowired
    private StationManageService stationManageService;

    /**
     * 根据站点名称、站长名称、站点电话获取对应的列表信息
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid list(@RequestBody StationManageQueryObject qo) {
        return stationManageService.pageList(qo);
    }

    /**
     * 获取区域树集合
     * @return
     */
    @PostMapping("/tree")
    public List<BaseTreeNode> tree(){
        return stationManageService.tree();
    }

    /**
     * 添加或编辑驿站信息
     * @param stationManage
     * @return
     */
    @OperateLog(title = "添加或编辑销售区域（社区）信息", businessType = BusinessType.UPDATE)
    @PostMapping("/addOrEdit")
    public R addOrEdit(@RequestBody StationManage stationManage){
        return stationManageService.addOrEdit(stationManage);
    }

    /**
     * 驿站状态开关：编辑
     * @param value
     * @param id
     * @return
     */
    @PostMapping("/stationStatusEdit")
    public R stationStatusEdit(@RequestParam("value") String value, @RequestParam("id") String id){
        return stationManageService.stationStatusEdit(value,id);
    }

    /**
     * 驿站审核申请
     * @param ids
     * @return
     */
    @PostMapping("/checkByIds")
    public R checkByIds(@RequestParam("ids") String ids){
        return stationManageService.checkByIds(ids);
    }

    /**
     * 根据ID删除信息
     * @param ids
     * @return
     */
    @PostMapping("/delByIds")
    public R delByIds(@RequestParam("ids") String ids){
        return stationManageService.delByIds(ids);
    }
}
