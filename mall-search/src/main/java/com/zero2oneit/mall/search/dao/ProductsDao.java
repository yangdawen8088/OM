package com.zero2oneit.mall.search.dao;

import com.zero2oneit.mall.common.bean.goods.Products;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsDao extends ElasticsearchRepository<Products,Long> {


}