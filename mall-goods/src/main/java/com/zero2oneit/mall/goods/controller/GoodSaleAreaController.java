package com.zero2oneit.mall.goods.controller;

import com.zero2oneit.mall.common.annotion.OperateLog;
import com.zero2oneit.mall.common.bean.goods.GoodSaleArea;
import com.zero2oneit.mall.common.bean.goods.GoodSaleAreaTree;
import com.zero2oneit.mall.common.enums.BusinessType;
import com.zero2oneit.mall.common.query.goods.GoodSaleAreaQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.goods.service.GoodSaleAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description:
 *
 * @author yjj
 * @date 2021/2/24 15:53
 */
@RestController
@RequestMapping("/admin/goodSaleArea")
public class GoodSaleAreaController {

    @Autowired
    private GoodSaleAreaService goodSaleAreaService;

    /**
     * 获取销售区域(社区)树集合
     * @return
     */
    @PostMapping("/tree")
    public List<GoodSaleAreaTree> tree(){
        return goodSaleAreaService.tree();
    }

    /**
     * 根据区域IDS获取对应的列表信息
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid list(@RequestBody GoodSaleAreaQueryObject qo) {
        return goodSaleAreaService.pageList(qo);
    }

    /**
     * 添加或编辑销售区域（社区）信息
     * @param goodSaleArea
     * @return
     */
    @OperateLog(title = "添加或编辑销售区域（社区）信息", businessType = BusinessType.UPDATE)
    @PostMapping("/addOrEdit")
    public R addOrEdit(@RequestBody GoodSaleArea goodSaleArea){
        return goodSaleAreaService.addOrEdit(goodSaleArea);
    }

    /**
     * 更改销售区域（社区）信息状态
     * @param id
     * @return
     */
    @PostMapping("/status")
    public R status(String id){
        return goodSaleAreaService.status(id);
    }

    /**
     * 获取商品生产地(社区)树集合
     * @return
     */
    @PostMapping("/productPlaceTree")
    public List<GoodSaleAreaTree> productPlaceTree(){
        return goodSaleAreaService.productPlaceTree();
    }
}
