package com.zero2oneit.mall.service.goods.remote;

import com.zero2oneit.mall.common.annotion.OperateLog;
import com.zero2oneit.mall.common.bean.goods.CommunityCategory;
import com.zero2oneit.mall.common.bean.goods.GoodsCategory;
import com.zero2oneit.mall.common.enums.BusinessType;
import com.zero2oneit.mall.common.query.goods.CommunityCategoryQueryObject;
import com.zero2oneit.mall.common.query.goods.GoodsCategoryQueryObject;
import com.zero2oneit.mall.common.utils.Assert;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.suggest.Suggest;
import com.zero2oneit.mall.feign.goods.CommunityCategoryFeign;
import com.zero2oneit.mall.feign.goods.GoodsCategoryFeign;
import com.zero2oneit.mall.feign.oss.OssFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Description: 远程调用商品分类（全国）服务
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/2/5
 */
@RestController
@RequestMapping("/remote/goodsCategory")
@CrossOrigin
public class GoodsCategoryRemote {

    @Autowired
    private GoodsCategoryFeign categoryFeign;

    @Autowired
    private OssFeign ossFeign;

    /**
     * 根据菜单IDS获取对应的列表信息
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid list(@RequestBody GoodsCategoryQueryObject qo){
        return categoryFeign.list(qo);
    }

    /**
     * 添加或编辑商品分类（全国）信息
     * @param category
     * @return
     */
    @OperateLog(title = "添加或编辑商品分类（全国）信息", businessType = BusinessType.UPDATE)
    @PostMapping("/addOrEdit")
    public R addOrEdit(GoodsCategory category, @RequestParam("file") MultipartFile[] file){

        String imgUrl = null;
        if (file != null && file.length >= 1) {
            imgUrl = ossFeign.uploadImage(file, "goodsCategory");
            if(category.getId() == null){
                category.setIconCls(imgUrl);
            }else {
                category.setIconCls(imgUrl + (category.getIconCls().length() > 0 ? ","+category.getIconCls() : ""));
            }
        }

        return categoryFeign.addOrEdit(category);
    }

    /**
     * 获取商品分类(全国)树集合
     * @return
     */
    @PostMapping("/tree")
    public List<GoodsCategory> tree() {
        return categoryFeign.tree();
    }

    /**
     * 更改商品分类（全国）信息状态
     * @param id
     * @param status
     * @return
     */
    @OperateLog(title = "更改商品分类（全国）状态", businessType = BusinessType.UPDATE)
    @PostMapping("/status")
    public R status(String id, Integer status){
        return categoryFeign.status(id, status);
    }

    /**
     * 根据分类名称匹配商品分类数据
     * @param keyword
     * @return
     */
    @RequestMapping("/getCategoryByName")
    public Suggest getCategoryByName(String keyword) {
        return categoryFeign.getCategoryByName(keyword);
    }
}
