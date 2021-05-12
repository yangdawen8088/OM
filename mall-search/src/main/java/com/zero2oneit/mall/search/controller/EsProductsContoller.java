package com.zero2oneit.mall.search.controller;

import com.zero2oneit.mall.common.bean.goods.Products;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.search.dao.ProductsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/5/6
 */
@RestController
@RequestMapping("/admin/es/product")
public class EsProductsContoller {

    @Autowired
    private ProductsDao productsDao;

    /**
     * 添加或编辑商品信息
     * @param products
     * @return
     */
    @PostMapping("/addOrEdit")
    public void addOrEdit(@RequestBody List<Products> products){
        productsDao.saveAll(products);
    }

    /**
     * 删除商品（社区）信息状态
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public void delete(String id){
        productsDao.deleteById(Long.valueOf(id));
    }

}
