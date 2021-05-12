package com.zero2oneit.mall.service.goods.remote;

import com.zero2oneit.mall.common.annotion.OperateLog;
import com.zero2oneit.mall.common.bean.goods.GoodsBrand;
import com.zero2oneit.mall.common.bean.goods.GoodsCategory;
import com.zero2oneit.mall.common.enums.BusinessType;
import com.zero2oneit.mall.common.query.goods.GoodsBrandQueryObject;
import com.zero2oneit.mall.common.query.goods.GoodsCategoryQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.suggest.Suggest;
import com.zero2oneit.mall.feign.goods.GoodsBrandFeign;
import com.zero2oneit.mall.feign.goods.GoodsCategoryFeign;
import com.zero2oneit.mall.feign.oss.OssFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Description: 远程调用商品品牌（全国）服务
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/2/5
 */
@RestController
@RequestMapping("/remote/goodsBrand")
@CrossOrigin
public class GoodsBrandRemote {

    @Autowired
    private GoodsBrandFeign goodsBrandFeign;

    @Autowired
    private OssFeign ossFeign;

    /**
     * 根据商品分类IDS获取对应的品牌列表信息
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid list(@RequestBody GoodsBrandQueryObject qo){
        return goodsBrandFeign.list(qo);
    }

    /**
     * 添加或编辑商品品牌（全国）信息
     * @param brand
     * @return
     */
    @OperateLog(title = "添加或编辑商品品牌（全国）信息", businessType = BusinessType.UPDATE)
    @PostMapping("/addOrEdit")
    public R addOrEdit(GoodsBrand brand, @RequestParam("file") MultipartFile[] file){
        String imgUrl = null;
        if (file != null && file.length >= 1) {
            imgUrl = ossFeign.uploadImage(file, "goodsBrand");
            if(brand.getId() == null){
                brand.setLogo(imgUrl);
            }else {
                brand.setLogo(imgUrl + (brand.getLogo().length() > 0 ? ","+brand.getLogo() : ""));
            }
        }
        return goodsBrandFeign.addOrEdit(brand);
    }

    /**
     * 更改商品分类（全国）信息状态
     * @param id
     * @param status
     * @return
     */
    @OperateLog(title = "更改商品品牌（全国）状态", businessType = BusinessType.UPDATE)
    @PostMapping("/status")
    public R status(String id, Integer status){
        return goodsBrandFeign.status(id, status);
    }

    /**
     * 根据品牌名称匹配品牌数据
     * @param keyword
     * @return
     */
    @RequestMapping("/getGoodsBrandByName")
    public Suggest getGoodsBrandByName(String keyword) {
        return goodsBrandFeign.getGoodsBrandByName(keyword);
    }
}
