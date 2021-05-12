package com.zero2oneit.mall.service.goods.remote;

import com.zero2oneit.mall.common.annotion.OperateLog;
import com.zero2oneit.mall.common.bean.goods.GoodsSpecs;
import com.zero2oneit.mall.common.bean.market.Advert;
import com.zero2oneit.mall.common.enums.BusinessType;
import com.zero2oneit.mall.common.query.goods.SpecsQueryObject;
import com.zero2oneit.mall.common.query.market.AdvertQueryObject;
import com.zero2oneit.mall.common.utils.Assert;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.feign.goods.SpecsFeign;
import com.zero2oneit.mall.feign.market.AdvertFeign;
import com.zero2oneit.mall.feign.oss.OssFeign;
import com.zero2oneit.mall.system.base.utils.UserContext;
import com.zero2oneit.mall.system.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description: 远程调用商品规格服务
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/2/2
 */
@RestController
@RequestMapping("/remote/specs")
@CrossOrigin
public class SpecsRemote {

    @Autowired
    private SpecsFeign specsFeign;

    /**
     * 商品规格列表
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid list(@RequestBody SpecsQueryObject qo){
        return specsFeign.list(qo);
    }

    /**
     * 添加或编辑商品规格信息
     * @param goodsSpecs
     * @return
     */
    @OperateLog(title = "添加或编辑商品规格信息", businessType = BusinessType.UPDATE)
    @PostMapping("/addOrEdit")
    public R addOrEdit(GoodsSpecs goodsSpecs){
        return specsFeign.addOrEdit(goodsSpecs);
    }

    /**
     * 删除商品规格信息
     * @param ids
     * @return
     */
    @OperateLog(title = "删除商品规格信息", businessType = BusinessType.DELETE)
    @PostMapping("/deleteByIds")
    public R delAdvertByIds(String ids){
        Assert.notNull(ids, "请选择操作项");
        return specsFeign.deleteByIds(ids);
    }

}
