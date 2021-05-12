package com.zero2oneit.mall.service.goods.remote;

import com.zero2oneit.mall.common.annotion.OperateLog;
import com.zero2oneit.mall.common.bean.goods.CommunityCategory;
import com.zero2oneit.mall.common.enums.BusinessType;
import com.zero2oneit.mall.common.query.goods.CommunityCategoryQueryObject;
import com.zero2oneit.mall.common.utils.Assert;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.suggest.Suggest;
import com.zero2oneit.mall.feign.goods.CommunityCategoryFeign;
import com.zero2oneit.mall.feign.oss.OssFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description: 远程调用商品分类（社区团购）服务
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/2/5
 */
@RestController
@RequestMapping("/remote/communityCategory")
@CrossOrigin
public class CommunityCategoryRemote {

    @Autowired
    private OssFeign ossFeign;

    @Autowired
    private CommunityCategoryFeign categoryFeign;

    /**
     * 查询商品分类（社区团购）列表信息
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid list(@RequestBody CommunityCategoryQueryObject qo){
        return categoryFeign.list(qo);
    }

    /**
     * 添加或编辑商品分类（社区团购）信息
     * @param category
     * @return
     */
    @OperateLog(title = "添加或编辑商品分类（社区团购）信息", businessType = BusinessType.UPDATE)
    @PostMapping("/addOrEdit")
    public R addOrEdit(CommunityCategory category, @RequestParam("file") MultipartFile[] file){
        String imgUrl = null;
        if (file != null && file.length >= 1) {
            imgUrl = ossFeign.uploadImage(file, "communityCategory");
            if(category.getId() == null){
                category.setLogo(imgUrl);
            }else {
                category.setLogo(imgUrl + (category.getLogo().length() > 0 ? ","+category.getLogo() : ""));
            }
        }
        return categoryFeign.addOrEdit(category);
    }

    /**
     * 删除商品分类（社区团购）信息
     * @param ids
     * @return
     */
    @OperateLog(title = "删除商品分类（社区团购）信息", businessType = BusinessType.DELETE)
    @PostMapping("/deleteByIds")
    public R delByIds(String ids, String urls){
        Assert.notNull(ids, "请选择操作项");
        categoryFeign.deleteByIds(ids);
        ossFeign.deleteImage(urls);
        return R.ok();
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
