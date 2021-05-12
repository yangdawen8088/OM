package com.zero2oneit.mall.service.market.remote;

import com.zero2oneit.mall.common.annotion.OperateLog;
import com.zero2oneit.mall.common.bean.market.Advert;
import com.zero2oneit.mall.common.enums.BusinessType;
import com.zero2oneit.mall.common.query.market.AdvertQueryObject;
import com.zero2oneit.mall.common.utils.Assert;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.feign.market.AdvertFeign;
import com.zero2oneit.mall.feign.oss.OssFeign;
import com.zero2oneit.mall.system.base.utils.UserContext;
import com.zero2oneit.mall.system.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description: 远程调用广告管理服务
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/2/2
 */
@RestController
@RequestMapping("/remote/advert")
@CrossOrigin
public class AdvertRemote {

    @Autowired
    private OssFeign ossFeign;

    @Autowired
    private AdvertFeign advertFeign;

    /**
     * 查询广告列表信息
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid list(@RequestBody AdvertQueryObject qo){
        return advertFeign.list(qo);
    }

    /**
     * 添加或编辑广告信息
     * @param advert
     * @return
     */
    @OperateLog(title = "添加或编辑广告信息", businessType = BusinessType.UPDATE)
    @PostMapping("/addOrEdit")
    public R addOrEdit(Advert advert, @RequestParam("file") MultipartFile[] file){
        String imgUrl = null;
        if (file != null && file.length >= 1) {
            imgUrl = ossFeign.uploadImage(file, "advert");
            if(advert.getId() == null){
                advert.setPicUrl(imgUrl);
            }else {
                advert.setPicUrl(imgUrl + (advert.getPicUrl().length() > 0 ? ","+advert.getPicUrl() : ""));
            }
        }

        User user = UserContext.getCurrentUser(UserContext.SYS_USER);
        advert.setOperatorId(user.getId());
        //添加或编辑广告信息
        return advertFeign.addOrEdit(advert);
    }

    /**
     * 删除广告信息
     * @param ids
     * @return
     */
    @OperateLog(title = "删除广告信息", businessType = BusinessType.DELETE)
    @PostMapping("/deleteByIds")
    public R delByIds(String ids, String urls, String advertPosition){
        Assert.notNull(ids, "请选择操作项");
        advertFeign.deleteByIds(ids, advertPosition);
//        ossFeign.deleteImage(urls);
        return R.ok();
    }

}
