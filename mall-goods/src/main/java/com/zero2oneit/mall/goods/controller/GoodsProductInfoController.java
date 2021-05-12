package com.zero2oneit.mall.goods.controller;

import com.zero2oneit.mall.common.bean.goods.*;
import com.zero2oneit.mall.common.query.goods.GoodsCategoryQueryObject;
import com.zero2oneit.mall.common.query.goods.GoodsProductInfoQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.snowflake.IdWorker;
import com.zero2oneit.mall.common.utils.snowflake.IdWorkerFactory;
import com.zero2oneit.mall.goods.feign.SearchFeign;
import com.zero2oneit.mall.goods.service.GoodsProductInfoRelationService;
import com.zero2oneit.mall.goods.service.GoodsProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * Description: 商品管理（社区）
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-22
 */
@RestController
@RequestMapping("/admin/communityGoods")
public class GoodsProductInfoController {

    @Autowired
    private GoodsProductInfoService goodsProductService;

    @Autowired
    private GoodsProductInfoRelationService goodsRelationService;

    @Autowired
    private SearchFeign searchFeign;

    /**
     * 根据不同状态获取对应的列表信息
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid list(@RequestBody GoodsProductInfoQueryObject qo) {
        return goodsProductService.pageList(qo);
    }

    /**
     * 添加或编辑商品分类（社区团购）信息
     * @param goods
     * @return
     */
    @PostMapping("/addOrEdit")
    public R addOrEdit(@RequestBody GoodsProductInfo goods){

        GoodsProductInfoRelation relation = null;
        for (int i = 0; i < goods.getMarketPrice().split(",").length; i++) {
                if(goods.getId() == null){
                    //生成分布式商品ID 保存商品表
                    IdWorker idworker = IdWorkerFactory.create(0,0);
                    goods.setId(idworker.nextId());
                    goodsProductService.save(goods);
                    relation = getGoodsProductInfoRelation(goods, i);
                    goodsRelationService.save(relation);
                    goods.setId(null);
                }else{
                    goods.setProductStatus(0);
                    goodsProductService.updateById(goods);
                    relation = getGoodsProductInfoRelation(goods, i);
                    goodsRelationService.updateById(relation);
                }
        }

        return R.ok();
    }

    private GoodsProductInfoRelation getGoodsProductInfoRelation(GoodsProductInfo goods, int i) {
        GoodsProductInfoRelation relation;
        relation = new GoodsProductInfoRelation();
        relation.setId(goods.getId());
        relation.setProductName(goods.getProductName());
        relation.setMarketPrice(new BigDecimal(goods.getMarketPrice().split(",")[i]));
        relation.setSupplyPrice(new BigDecimal(goods.getSupplyPrice().split(",")[i]));
        relation.setProductPrice(new BigDecimal(goods.getProductPrice().split(",")[i]));
        relation.setProductStock(Integer.valueOf(goods.getProductStock().split(",")[i]));
        relation.setSaleAreaName(goods.getSaleAreaName().split(",")[i]);
        relation.setSaleAmount(Integer.valueOf(goods.getSaleAmount().split(",")[i]));
        return relation;
    }

    /**
     * 更改商品（社区）信息状态
     * @param id
     * @param status
     * @return
     */
    @PostMapping("/status")
    public R status(String id, Integer status){
        goodsProductService.status(id, status);
        if(status == 4){
            searchFeign.delete(id);
        }
        return R.ok();
    }

    /**
     * 商品审核（社区）信息状态
     * @param ids
     * @param productStatus
     * @param approveResult
     * @return
     */
    @PostMapping("/audit")
    public R audit(String ids, Integer productStatus, String approveResult, String userName){
        goodsProductService.audit(ids, productStatus, approveResult, userName);
        if(productStatus == 1){
            //添加或编辑ES中的通用商品
            List<Products> products = goodsProductService.esList(ids);
            if(products.size() > 0){
                searchFeign.addOrEdit(goodsProductService.esList(ids));
            }
        }
        return R.ok();
    }

    /**
     * 绑定秒杀商品
     * @param productIds
     * @param ruleId
     * @return
     */
    @PostMapping("/bindSecKill")
    public R bindSecKill(String productIds, String ruleId){
        goodsProductService.bindSecKill(productIds, ruleId);
        return R.ok();
    }

    /**
     * 解绑秒杀商品
     * @param productIds
     * @return
     */
    @PostMapping("/unSeckill")
    public R unSeckill(String productIds){
        goodsProductService.unSeckill(productIds);
        return R.ok();
    }

    /**
     * 加载某个商品（社区）信息
     * @param id
     * @return
     */
    @PostMapping("/load")
    public R load(String id){
        return R.ok(goodsProductService.load(id));
    }

    /**
     * 复制某个商品（社区）信息
     * @param id
     * @return
     */
    @PostMapping("/copy")
    public R copy(String id){
        goodsProductService.copy(id);
        return R.ok("复制商品成功");
    }

}
