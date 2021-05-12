package com.zero2oneit.mall.search.config;


import com.zero2oneit.mall.common.bean.goods.Products;
import com.zero2oneit.mall.search.dao.ProductDao;
import com.zero2oneit.mall.search.dao.ProductsDao;
import com.zero2oneit.mall.search.qo.Product;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/5/2
 */
@SpringBootTest
public class ESConfigTest {

    //注入ElasticsearchRestTemplate
    @Autowired
    private ElasticsearchRestTemplate template;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductsDao productsDao;

    //创建索引并增加映射配置
    @Test
    public void createIndex(){
        //创建索引，系统初始化会自动创建索引
        System.out.println("创建索引");
    }

    //删除索引
    @Test
    public void deleteIndex(){
        //创建索引，系统初始化会自动创建索引
        boolean flg = template.deleteIndex(Product.class);
        System.out.println("删除索引 = " + flg);
    }

    /**
     * 新增
     */
    @Test
    public void save(){
        Product product = new Product();
        product.setId(2L);
        product.setTitle("华为手机");
        product.setCategory("手机");
        product.setPrice(2999.0);
        product.setImages("http://www.atguigu/hw.jpg");
        productDao.save(product);
    }

    //修改
    @Test
    public void update(){
        Product product = new Product();
        product.setId(2L);
        product.setTitle("小米2手机");
        product.setCategory("手机");
        product.setPrice(9999.0);
        product.setImages("http://www.atguigu/xm.jpg");
        productDao.save(product);
    }

    //根据id查询
    @Test
    public void findById(){
        Product product = productDao.findById(2L).get();
        System.out.println(product);
    }

    //查询所有
    @Test
    public void findAll(){
        Iterable<Products> products = productsDao.findAll();
        for (Products product : products) {
            System.out.println(product);
        }
    }

    //删除
    @Test
    public void delete(){
        Product product = new Product();
        product.setId(1L);
        productDao.delete(product);
    }

    //批量新增
    @Test
    public void saveAll(){
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setId(Long.valueOf(i));
            product.setTitle("小米手机"+i);
            product.setCategory("手机");
            product.setPrice((100.00*new Random().nextInt(10) + 1));
            product.setImages("http://www.atguigu/xm.jpg");
            productList.add(product);
        }
        productDao.saveAll(productList);
    }

    //分页查询
    @Test
    public void findByPageable(){
        //设置排序(排序方式，正序还是倒序，排序的id)
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        int currentPage=0;//当前页，第一页从0开始，1表示第二页
        int pageSize = 5;//每页显示多少条
        //设置查询分页
        PageRequest pageRequest = PageRequest.of(currentPage, pageSize,sort);
        //分页查询
        Page<Product> productPage = productDao.findAll(pageRequest);
        for (Product Product : productPage.getContent()) {
            System.out.println(Product);
        }
    }

    /**
     * term查询
     * search(termQueryBuilder) 调用搜索方法，参数查询构建器对象
     */
    @Test
    public void termQuery(){
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", "小米");
        Iterable<Product> products = productDao.search(termQueryBuilder);
        for (Product product : products) {
            System.out.println(product);
        }
    }

    /**
     * term查询加分页
     */
    @Test
    public void termQueryByPage(){
        //设置排序(排序方式，正序还是倒序，排序的id)
        int currentPage= 0 ;
        int pageSize = 5;
        //设置查询分页
        PageRequest pageRequest = PageRequest.of(currentPage, pageSize);
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", "小米手机9");
        Iterable<Product> products = productDao.search(termQueryBuilder,pageRequest);
        for (Product product : products) {
            System.out.println(product);
        }
    }

    /**
     * 聚合查询加分页
     */
    @Test
    public void queryByTitlePage(){

        List<Products> products = new ArrayList<>();
        //设置高亮字段
//        HighlightBuilder.Field desc=new HighlightBuilder.Field("desc")
//                //关闭多个字段匹配 true表示只有查询的字段匹配高亮  false表示多个字段都匹配
//                .requireFieldMatch(false)
//                .preTags("<span style='color:red'>")
//                .postTags("</span>");
//
//        HighlightBuilder.Field name=new HighlightBuilder.Field("name")
//                //关闭多个字段匹配
//                .requireFieldMatch(false)
//                .preTags("<span style='color:red'>")
//                .postTags("</span>");
        //高亮多个字段设置
      /*  HighlightBuilder highlightBuilder=new HighlightBuilder();
        highlightBuilder.field("title");
        highlightBuilder.requireFieldMatch(false);
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
*/
        //简单字段查询 查询User下的索引
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                //以关键字“小米”，关键字支持分词
                .withQuery(QueryBuilders.matchQuery("productName","木"))
                //指定字符串作为关键词查询，关键词支持分词
//                .withQuery(QueryBuilders.queryStringQuery("小米手机1").field("title"))
                //以关键字“小米”，关键字不支持分词
//                .withQuery(QueryBuilders.termQuery("title","小米手机1"))
                //添加分页，注意页码从0开始
                ///pageable的实现类PageRequest的静态方法of
                .withPageable(PageRequest.of(1,10))
                //过滤大小 lt 小于 gt大于  lte小于等于 gte大于等于
//                .withFilter(QueryBuilders.rangeQuery("age").gte(10))
                //排序 使用字段排序返回结果中的score为null
                //根据字段排序fieldSort("字段名")   .order(SortOrder.ASC/DESC)
                .withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC))
//                .withHighlightFields(name,desc)
//                .withHighlightBuilder(highlightBuilder)
                .build();
        //获取hits数据


        SearchHits<Products> search = template.search(query, Products.class);
        System.out.println(search);
        search.forEach(u->
                products.add(u.getContent())
        );

        System.out.println("products = " + products);
    }

    /**
     * term查询加分页
     */
    @Test
    public void page(){

        //创建查询构建器
        NativeSearchQueryBuilder QueryBuilder = new NativeSearchQueryBuilder();

        /**
         * 给查询构造器添加条件
         *   1. 它仍然需要的是一个  QueryBuilder ,通过QueryBuilders里面的静态方法创建,间接继承了QueryBuild
         *   2. 可也看到,基本上所有常用的条件查询都有了, bool , 词条term , match , 模糊查询 fuzzy
         *   3. 我们也可以抽出来一个方法, 方法里使用bool查询, 他支持先添加查询,紧接着过滤,  还记不记得那个  match{},filter:{}
         *    3.1 注意区分开结果过滤_source 和 filter
         *
         *         matchQuery     @Param field
         *         matchQuery     @Param field的值
         *   */
        QueryBuilder.withQuery(QueryBuilders.matchQuery("title","小米"));

        /**
         * 结果过滤
         * @Param : SourceFilter  ,但是它是和接口,于是我用它唯一的实现类
         */
//        QueryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{"title","price"},null));

        /**
         * 排序
         */
        QueryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));

        /**
         * 分页
         *  原生的api使用下面两个字段控制
         *  "from": 0,
         *  "size":10
         *  但是注意,他是的第一页是0
         * @Param : Pageable 他是个接口,我们使用它的实现类  PageRequest(静态方法of)
         */
        QueryBuilder.withPageable(PageRequest.of(1,10));

        Page<Product> result = productDao.search(QueryBuilder.build());  //它仍然需要的是一个QueryBuilder , 通过构造器.build()构建出来

        //解析结果
        long elements = result.getTotalElements();
        int totalPages = result.getTotalPages();
        List<Product> content = result.getContent();

    }

}