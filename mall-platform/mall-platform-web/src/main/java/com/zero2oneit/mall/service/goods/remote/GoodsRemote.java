package com.zero2oneit.mall.service.goods.remote;

import com.zero2oneit.mall.common.annotion.OperateLog;
import com.zero2oneit.mall.common.bean.goods.GoodsSpu;
import com.zero2oneit.mall.common.enums.BusinessType;
import com.zero2oneit.mall.common.query.goods.GoodsSpuQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.feign.goods.GoodsCategoryFeign;
import com.zero2oneit.mall.feign.goods.GoodsFeign;
import com.zero2oneit.mall.feign.oss.OssFeign;
import com.zero2oneit.mall.system.base.utils.UserContext;
import com.zero2oneit.mall.system.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 远程调用商品管理（社区团购）服务
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/2/5
 */
@RestController
@RequestMapping("/remote/goods")
@CrossOrigin
public class GoodsRemote {

    @Autowired
    private OssFeign ossFeign;

    @Autowired
    private GoodsCategoryFeign categoryFeign;

    @Autowired
    private GoodsFeign goodsFeign;

    /**
     * 获取商品分类(全国)树集合
     * @return
     */
    @PostMapping("/tree")
    public R tree() {
        return R.ok(categoryFeign.tree());
    }

    /**
     * sku 上传图片
     * @param file
     * @return
     */
    @PostMapping("/images")
    public R images(@RequestParam("file") MultipartFile[] file){
        String imgUrl = "";
        if (file != null && file.length >= 1) {
            imgUrl = ossFeign.uploadImage(file, "goods/productPicture");
        }
        Map map = new HashMap<>();
        map.put("imgUrl", imgUrl);
        return R.ok(map);
    }

    /**
     * 添加或编辑商品（全国）信息
     * @param goods
     * @return
     */
    @OperateLog(title = "添加或编辑商品（全国）信息", businessType = BusinessType.UPDATE)
    @PostMapping("/addOrEdit")
    public R addOrEdit(GoodsSpu goods, @RequestParam("fileOne") MultipartFile[] fileOne, @RequestParam("fileTwo") MultipartFile[] fileTwo,
                       @RequestParam("fileFive") MultipartFile[] fileFive){

        if (fileOne != null && fileOne.length >= 1) {//主图
            String imgUrl = ossFeign.uploadImage(fileOne, "goods/qg/mainPicture");
            if(goods.getId() == null){
                goods.setMainPicture(imgUrl);
            }else {
                goods.setMainPicture(imgUrl + (goods.getMainPicture().length() > 0 ? ","+goods.getMainPicture() : ""));
            }
        }

        if (fileTwo != null && fileTwo.length >= 1) {//轮播图
            String imgUrl = ossFeign.uploadImage(fileTwo, "goods/qg/detailPicture");
            if(goods.getId() == null){
                goods.setDetailPicture(imgUrl);
            }else {
                goods.setDetailPicture(imgUrl + (goods.getDetailPicture().length() > 0 ? ","+goods.getDetailPicture() : ""));
            }
        }

        if (fileFive != null && fileFive.length >= 1) {//实物图片
            String imgUrl = ossFeign.uploadImage(fileFive, "goods/qg/video");
            if(goods.getId() == null){
                goods.setVideoUrl(imgUrl);
            }else {
                goods.setVideoUrl(imgUrl + (goods.getVideoUrl().length() > 0 ? ","+goods.getVideoUrl() : ""));
            }
        }

        goodsFeign.addOrEdit(goods);

        return R.ok();
    }

    /**
     * 商品管理列表统计
     * @return
     */
    @PostMapping("/report")
    public R report(){
        Map map = goodsFeign.report();
        return R.ok(map);
    }

    /**
     * 查询商品（全国）列表信息
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid list(@RequestBody GoodsSpuQueryObject qo){
        return goodsFeign.list(qo);
    }

    /**
     * 更改商品（全国）信息状态
     * @param id
     * @param status
     * @return
     */
    @OperateLog(title = "更改商品（全国）状态", businessType = BusinessType.UPDATE)
    @PostMapping("/status")
    public R status(String id, Integer status){
        return goodsFeign.status(id, status);
    }

    /**
     * 商品审核（全国）信息状态
     * @param ids
     * @param productStatus
     * @param approveResult
     * @return
     */
    @OperateLog(title = "商品审核（全国）信息状态", businessType = BusinessType.UPDATE)
    @PostMapping("/audit")
    public R audit(String ids, Integer productStatus, String approveResult){
        User user = UserContext.getCurrentUser(UserContext.SYS_USER);
        return goodsFeign.audit(ids, productStatus, approveResult, user.getUsername());
    }

    /**
     * 加载某个商品（全国）信息
     * @param id
     * @return
     */
    @OperateLog(title = "商品审核（全国）信息", businessType = BusinessType.OTHER)
    @PostMapping("/load")
    public R load(String id){
        return goodsFeign.load(id);
    }

    /**
     * 加载某个商品（全国）sku信息
     * @param id
     * @return
     */
    @OperateLog(title = "商品审核（全国）sku信息", businessType = BusinessType.OTHER)
    @PostMapping("/loadSku")
    public R loadSku(String id){
        return R.ok(goodsFeign.loadSku(id));
    }

}
