package com.zero2oneit.mall.search.api;

import com.zero2oneit.mall.common.bean.goods.Products;
import com.zero2oneit.mall.common.query.goods.GoodsProductInfoQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.search.dao.ProductsDao;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/5/2
 */
@RestController
@RequestMapping("/api/search/products")
public class SearchApi {

    @Autowired
    private ProductsDao productsDao;

    @Autowired
    private ElasticsearchRestTemplate template;

    /**
     * 查询商品列表
     * @return
     */
    @PostMapping("/pageByName")
    public R pageByName(@RequestBody GoodsProductInfoQueryObject qo){
        List<Products> products = new ArrayList<>();
        //简单字段查询 查询User下的索引
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                //以关键字“小米”，关键字支持分词
                .withQuery(QueryBuilders.matchQuery("productName",qo.getProductName()))
                //添加分页，注意页码从0开始 然后 1、2、3.。。
                .withPageable(PageRequest.of(qo.getCurrentPage(), qo.getPageSize()))
                //按大小排序 越小排在前面
                .withSort(SortBuilders.fieldSort("productSort").order(SortOrder.ASC))
                .build();
        SearchHits<Products> search = template.search(query, Products.class);
        System.out.println(search);
        search.forEach(u->
                products.add(u.getContent())
        );
        return R.ok("加载成功", new BoostrapDataGrid(new Long(search.getTotalHits()).intValue(), products));
    }

    /**
     * 查询商品列表
     * @return
     */
    @PostMapping("/pageByType")
    public R pageByType(@RequestBody GoodsProductInfoQueryObject qo){
        List<Products> products = new ArrayList<>();
        //简单字段查询 查询User下的索引
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                //以关键字“小米”，关键字支持分词
                .withQuery(QueryBuilders.matchQuery("typeId", qo.getTypeId()))
                .withQuery(QueryBuilders.matchQuery("moudleId", 0))
                //添加分页，注意页码从0开始 然后 1、2、3.。。
                .withPageable(PageRequest.of(qo.getCurrentPage(), qo.getPageSize()))
                //按大小排序 越小排在前面
                .withSort(SortBuilders.fieldSort("productSort").order(SortOrder.ASC))
                .build();
        SearchHits<Products> search = template.search(query, Products.class);
        search.forEach(u->
                products.add(u.getContent())
        );
        return R.ok("加载成功", new BoostrapDataGrid(new Long(search.getTotalHits()).intValue(), products));
    }

    /**
     * 查询秒杀商品列表
     * @return
     */
    @PostMapping("/secKill")
    public R secKill(@RequestBody GoodsProductInfoQueryObject qo){
        List<Products> products = new ArrayList<>();
        //简单字段查询 查询User下的索引
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                //以关键字“小米”，关键字支持分词
                .withQuery(QueryBuilders.matchQuery("moudleId", qo.getMoudleId()))
                //按大小排序 越小排在前面
                .withSort(SortBuilders.fieldSort("productSort").order(SortOrder.ASC))
                .build();
        SearchHits<Products> search = template.search(query, Products.class);
        search.forEach(u->
                products.add(u.getContent())
        );

        List list = new ArrayList();
        list.add(products.stream().filter(p -> p.getStartTime() == 23).collect(Collectors.toList()));
        list.add(products.stream().filter(p -> p.getStartTime() == 0).collect(Collectors.toList()));
        list.add(products.stream().filter(p -> p.getStartTime() == 6).collect(Collectors.toList()));
        list.add(products.stream().filter(p -> p.getStartTime() == 8).collect(Collectors.toList()));
        list.add(products.stream().filter(p -> p.getStartTime() == 9).collect(Collectors.toList()));
        list.add(products.stream().filter(p -> p.getStartTime() == 10).collect(Collectors.toList()));
        list.add(products.stream().filter(p -> p.getStartTime() == 12).collect(Collectors.toList()));
        list.add(products.stream().filter(p -> p.getStartTime() == 14).collect(Collectors.toList()));
        list.add(products.stream().filter(p -> p.getStartTime() == 16).collect(Collectors.toList()));
        list.add(products.stream().filter(p -> p.getStartTime() == 18).collect(Collectors.toList()));
        list.add(products.stream().filter(p -> p.getStartTime() == 20).collect(Collectors.toList()));

        return R.ok("加载成功", new BoostrapDataGrid(new Long(search.getTotalHits()).intValue(), list));
    }

    /**
     * 查询秒杀首页商品列表
     * @return
     */
    @PostMapping("/secKillPage")
    public R secKillPage(@RequestBody GoodsProductInfoQueryObject qo){
        List<Products> products = new ArrayList<>();
        //简单字段查询 查询User下的索引
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                //以关键字“小米”，关键字支持分词
                .withQuery(QueryBuilders.matchQuery("moudleId", qo.getMoudleId()))
                //按大小排序 越小排在前面
                .withPageable(PageRequest.of(qo.getCurrentPage(), qo.getPageSize()))
                .withSort(SortBuilders.fieldSort("productSort").order(SortOrder.ASC))
                .build();
        SearchHits<Products> search = template.search(query, Products.class);
        search.forEach(u->
                products.add(u.getContent())
        );
        return R.ok("加载成功", new BoostrapDataGrid(new Long(search.getTotalHits()).intValue(), products));
    }

}
