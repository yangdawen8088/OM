package com.zero2oneit.mall.goods.controller;

import com.zero2oneit.mall.common.bean.goods.GoodsSku;
import com.zero2oneit.mall.common.bean.goods.GoodsSpu;
import com.zero2oneit.mall.common.query.goods.GoodsSpuQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.snowflake.IdWorker;
import com.zero2oneit.mall.common.utils.snowflake.IdWorkerFactory;
import com.zero2oneit.mall.goods.service.GoodsSkuService;
import com.zero2oneit.mall.goods.service.GoodsSpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-03-08
 */
@RestController
@RequestMapping("/admin/goods")
public class GoodsSpuController {

    @Autowired
    private GoodsSpuService goodsSpuService;

    @Autowired
    private GoodsSkuService goodsSkuService;

    /**
     * 添加或编辑商品分类（社区团购）信息
     * @param goods
     * @return
     */
    @PostMapping("/addOrEdit")
    public R addOrEdit(@RequestBody GoodsSpu goods){

        IdWorker idworker = IdWorkerFactory.create(0,0);
        if(goods.getId() == null){
            goods.setId(idworker.nextId());
            goodsSpuService.save(goods);
            //保存sku信息
            for (int i = 0; i < goods.getMarketPrice().split(",").length; i++) {
                GoodsSku sku = new GoodsSku();
                sku.setId(idworker.nextId());
                sku.setGoodsId(goods.getId());
                sku.setProductName(goods.getProductName());
                sku.setSkuName(goods.getSkuName().split(",")[i]);
                sku.setProductStock(Integer.parseInt(goods.getProductStock().split(",")[i]));
                sku.setSupplyPrice(new BigDecimal(goods.getSupplyPrice().split(",")[i]));
                sku.setMarketPrice(new BigDecimal(goods.getMarketPrice().split(",")[i]));
                sku.setProductPrice(new BigDecimal(goods.getProductPrice().split(",")[i]));
                sku.setProductPicture(goods.getProductPicture().split(",")[i]);
                sku.setProductWeight(goods.getProductWeight().split(",")[i]);
                sku.setProductVolume(goods.getProductVolume().split(",")[i]);
                goodsSkuService.save(sku);
            }
        }else{
            goods.setProductStatus(0);
            goodsSpuService.updateById(goods);
            //保存sku信息
            for (int i = 0; i < goods.getMarketPrice().split(",").length; i++) {
                GoodsSku sku = new GoodsSku();
                sku.setId(Long.valueOf(goods.getSkuId().split(",")[i]));
                sku.setGoodsId(goods.getId());
                sku.setProductName(goods.getProductName());
                sku.setSkuName(goods.getSkuName().split(",")[i]);
                sku.setProductStock(Integer.parseInt(goods.getProductStock().split(",")[i]));
                sku.setSupplyPrice(new BigDecimal(goods.getSupplyPrice().split(",")[i]));
                sku.setMarketPrice(new BigDecimal(goods.getMarketPrice().split(",")[i]));
                sku.setProductPrice(new BigDecimal(goods.getProductPrice().split(",")[i]));
                sku.setProductPicture(goods.getProductPicture().split(",")[i]);
                sku.setProductWeight(goods.getProductWeight().split(",")[i]);
                sku.setProductVolume(goods.getProductVolume().split(",")[i]);
                goodsSkuService.updateById(sku);
            }
        }

        return R.ok();
    }

    /**
     * 商品管理列表统计
     * @return
     */
    @PostMapping("/report")
    public Map<String, Object> report(){
        return goodsSpuService.report();
    }

    /**
     * 根据不同状态获取对应的列表信息
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid list(@RequestBody GoodsSpuQueryObject qo) {
        return goodsSpuService.pageList(qo);
    }

    /**
     * 更改商品（全国）信息状态
     * @param id
     * @param status
     * @return
     */
    @PostMapping("/status")
    public R status(String id, Integer status){
        goodsSpuService.status(id, status);
        return R.ok();
    }

    /**
     * 商品审核（全国）信息状态
     * @param ids
     * @param productStatus
     * @param approveResult
     * @return
     */
    @PostMapping("/audit")
    public R audit(String ids, Integer productStatus, String approveResult, String userName){
        goodsSpuService.audit(ids, productStatus, approveResult, userName);
        return R.ok();
    }

    /**
     * 加载某个商品（全国）信息
     * @param id
     * @return
     */
    @PostMapping("/load")
    public R load(String id){
        return R.ok(goodsSpuService.load(id));
    }

    /**
     * 加载某个商品（全国）信息
     * @param id
     * @return
     */
    @PostMapping("/loadSku")
    public List<Map<String, Object>> loadSku(String id){
        return goodsSpuService.loadSku(id);
    }

}
