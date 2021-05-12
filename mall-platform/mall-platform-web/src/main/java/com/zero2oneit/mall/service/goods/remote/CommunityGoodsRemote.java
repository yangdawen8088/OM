package com.zero2oneit.mall.service.goods.remote;

import com.zero2oneit.mall.common.annotion.OperateLog;
import com.zero2oneit.mall.common.bean.goods.GoodsProductInfo;
import com.zero2oneit.mall.common.enums.BusinessType;
import com.zero2oneit.mall.common.query.goods.GoodsProductInfoQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.feign.goods.CommunityGoodsFeign;
import com.zero2oneit.mall.feign.oss.OssFeign;
import com.zero2oneit.mall.system.base.utils.UserContext;
import com.zero2oneit.mall.system.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description: 远程调用商品管理（社区团购）服务
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/2/5
 */
@RestController
@RequestMapping("/remote/communityGoods")
@CrossOrigin
public class CommunityGoodsRemote {

    @Autowired
    private CommunityGoodsFeign communityGoodsFeign;

    @Autowired
    private OssFeign ossFeign;

    /**
     * 查询商品（社区团购）列表信息
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid list(@RequestBody GoodsProductInfoQueryObject qo){
        return communityGoodsFeign.list(qo);
    }

    /**
     * 添加或编辑商品（社区团购）信息
     * @param goods
     * @return
     */
    @OperateLog(title = "添加或编辑商品（社区团购）信息", businessType = BusinessType.UPDATE)
    @PostMapping("/addOrEdit")
    public R addOrEdit(GoodsProductInfo goods, @RequestParam("fileOne") MultipartFile[] fileOne,@RequestParam("fileTwo") MultipartFile[] fileTwo,
                       @RequestParam("fileThree") MultipartFile[] fileThree, @RequestParam("fileFour") MultipartFile[] fileFour,
                       @RequestParam("fileFive") MultipartFile[] fileFive){

        if (fileOne != null && fileOne.length >= 1) {//主图
            String imgUrl = ossFeign.uploadImage(fileOne, "goods/mainPicture");
            if(goods.getId() == null){
                goods.setMainPicture(imgUrl);
            }else {
                goods.setMainPicture(imgUrl + (goods.getMainPicture().length() > 0 ? ","+goods.getMainPicture() : ""));
            }
        }

        if (fileTwo != null && fileTwo.length >= 1) {//详情图
            String imgUrl = ossFeign.uploadImage(fileTwo, "goods/detailPicture");
            if(goods.getId() == null){
                goods.setDetailPicture(imgUrl);
            }else {
                goods.setDetailPicture(imgUrl + (goods.getDetailPicture().length() > 0 ? ","+goods.getDetailPicture() : ""));
            }
        }

        if (fileThree != null && fileThree.length >= 1) {//白底图片（商品浏览图）
            String imgUrl = ossFeign.uploadImage(fileThree, "goods/whitePicture");
            if(goods.getId() == null){
                goods.setWhitePicture(imgUrl);
            }else {
                goods.setWhitePicture(imgUrl + (goods.getWhitePicture().length() > 0 ? ","+goods.getWhitePicture() : ""));
            }
        }

        if (fileFour != null && fileFour.length >= 1) {//实物图片
            String imgUrl = ossFeign.uploadImage(fileFour, "goods/physicalPicture");
            if(goods.getId() == null){
                goods.setPhysicalPicture(imgUrl);
            }else {
                goods.setPhysicalPicture(imgUrl + (goods.getPhysicalPicture().length() > 0 ? ","+goods.getPhysicalPicture() : ""));
            }
        }

        if (fileFive != null && fileFive.length >= 1) {//实物图片
            String imgUrl = ossFeign.uploadImage(fileFive, "goods/video");
            if(goods.getId() == null){
                goods.setVideoSrc(imgUrl);
            }else {
                goods.setVideoSrc(imgUrl + (goods.getVideoSrc().length() > 0 ? ","+goods.getVideoSrc() : ""));
            }
        }

        communityGoodsFeign.addOrEdit(goods);

        return R.ok();
    }

    /**
     * 获取商品生产地(社区)树集合
     * @return
     */
    @PostMapping("/productPlaceTree")
    public R productPlaceTree() {
        return R.ok(communityGoodsFeign.productPlaceTree());
    }

    /**
     * 更改商品（社区）信息状态
     * @param id
     * @param status
     * @return
     */
    @OperateLog(title = "更改商品（社区）状态", businessType = BusinessType.UPDATE)
    @PostMapping("/status")
    public R status(String id, Integer status){
        return communityGoodsFeign.status(id, status);
    }

    /**
     * 商品审核（社区）信息状态
     * @param ids
     * @param productStatus
     * @param approveResult
     * @return
     */
    @OperateLog(title = "商品审核（社区）信息状态", businessType = BusinessType.UPDATE)
    @PostMapping("/audit")
    public R audit(String ids, Integer productStatus, String approveResult){
        User user = UserContext.getCurrentUser(UserContext.SYS_USER);
        return communityGoodsFeign.audit(ids, productStatus, approveResult, user.getUsername());
    }

    /**
     * 绑定秒杀商品
     * @param productIds
     * @param ruleId
     * @return
     */
    @OperateLog(title = "绑定秒杀商品", businessType = BusinessType.OTHER)
    @PostMapping("/secKill")
    public R secKill(String productIds, String ruleId){
        return communityGoodsFeign.bindSecKill(productIds, ruleId);
    }

    /**
     * 解绑秒杀商品
     * @param productIds
     * @return
     */
    @OperateLog(title = "解绑秒杀商品", businessType = BusinessType.OTHER)
    @PostMapping("/unSeckill")
    public R unSeckill(String productIds){
        return communityGoodsFeign.unSeckill(productIds);
    }

    /**
     * 加载某个商品（社区）信息
     * @param id
     * @return
     */
    @OperateLog(title = "商品审核（社区）信息状态", businessType = BusinessType.OTHER)
    @PostMapping("/load")
    public R load(String id){
        return communityGoodsFeign.load(id);
    }

    /**
     * 复制某个商品（社区）信息
     * @param id
     * @return
     */
    @OperateLog(title = "复制商品", businessType = BusinessType.OTHER)
    @PostMapping("/copy")
    public R copy(String id){
        return communityGoodsFeign.copy(id);
    }

}
