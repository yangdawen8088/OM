package com.zero2oneit.mall.service.goods.remote;

import com.zero2oneit.mall.common.annotion.OperateLog;
import com.zero2oneit.mall.common.bean.goods.GoodSaleArea;
import com.zero2oneit.mall.common.bean.goods.GoodSaleAreaTree;
import com.zero2oneit.mall.common.enums.BusinessType;
import com.zero2oneit.mall.common.query.goods.GoodSaleAreaQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.feign.goods.GoodSaleAreaFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description: 远程调用销售区域（社区团购）服务
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/2/5
 */
@RestController
@RequestMapping("/remote/goodSaleArea")
@CrossOrigin
public class GoodSaleAreaRemote {

    @Autowired
    private GoodSaleAreaFeign goodSaleAreaFeign;

    /**
     * 获取销售区域(社区)树集合
     * @return
     */
    @PostMapping("/tree")
    public R tree() {
        return R.ok(goodSaleAreaFeign.tree());
    }

    /**
     * 根据区域IDS获取对应的列表信息
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid list(@RequestBody GoodSaleAreaQueryObject qo){
        return goodSaleAreaFeign.list(qo);
    }


    /**
     * 添加或编辑销售区域（社区）信息
     * @param goodSaleArea
     * @return
     */
    @OperateLog(title = "添加或编辑销售区域（社区）信息", businessType = BusinessType.UPDATE)
    @PostMapping("/addOrEdit")
    public R addOrEdit(GoodSaleArea goodSaleArea){
        return goodSaleAreaFeign.addOrEdit(goodSaleArea);
    }

    /**
     * 更改商品分类（全国）信息状态
     * @param id
     * @param id
     * @return
     */
    @OperateLog(title = "更改商品分类（全国）状态", businessType = BusinessType.UPDATE)
    @PostMapping("/status")
    public R status(String id){
        return goodSaleAreaFeign.status(id);
    }
}
