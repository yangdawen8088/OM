package com.zero2oneit.mall.goods.service;

import com.zero2oneit.mall.common.bean.goods.Products;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/5/6
 */
@SpringBootTest
public class GoodsProductInfoServiceTest {

    @Autowired
    private GoodsProductInfoService service;

    @Test
    public void esList() {

        List<Products> products = service.esList("2670783484624896");

        System.out.println("products = " + products);

    }

}