package com.zero2oneit.mall.search.dao;

import com.zero2oneit.mall.search.qo.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends ElasticsearchRepository<Product,Long> {


}