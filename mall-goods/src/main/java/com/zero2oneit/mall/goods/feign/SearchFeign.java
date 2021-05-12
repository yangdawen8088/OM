package com.zero2oneit.mall.goods.feign;

import com.zero2oneit.mall.common.bean.goods.Products;
import com.zero2oneit.mall.common.bean.goods.SecKillProducts;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/5/6
 */
@FeignClient("search-service")
public interface SearchFeign {

    @PostMapping("/admin/es/product/addOrEdit")
    void addOrEdit(@RequestBody List<Products> products);

    @PostMapping("/admin/es/product/delete")
    void delete(@RequestParam("id") String id);

}
