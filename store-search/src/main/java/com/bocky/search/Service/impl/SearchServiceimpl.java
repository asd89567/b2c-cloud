package com.bocky.search.Service.impl;


import com.bocky.parm.ProductSearchParm;
import com.bocky.pojo.Product;
import com.bocky.search.Service.SearchService;
import com.bocky.utils.R;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 　@Author: Bocky
 * 　@Date: 2023-04-28-下午 01:46
 * 　@Description:搜尋功能
 *
 */
@Service
@Slf4j
public class SearchServiceimpl  implements SearchService {

    @Resource
    private RestHighLevelClient restHighLevelClient;
    @Override
    public R search(ProductSearchParm productSearchParm) throws JsonProcessingException {
        SearchRequest searchRequest = new SearchRequest("product");
        String sh = productSearchParm.getSearch();
        if (StringUtils.isEmpty(sh)){
            //全部查詢
            searchRequest.source().query(QueryBuilders.matchAllQuery());
        }else {
            //部分查詢
             searchRequest.source().query(QueryBuilders.matchQuery("all", sh));
        }
        //設定分頁
        searchRequest.source().from(productSearchParm.getForm());
        searchRequest.source().size(productSearchParm.getPageSize());

        //查詢
        SearchResponse search;
        try {
             search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException("查詢錯誤");
        }
        SearchHits hits = search.getHits();
        //獲取數量
        long value = hits.getTotalHits().value;
        SearchHit[] hits1 = hits.getHits();
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Product> products = new ArrayList<>();
        for (SearchHit searchHit : hits1) {
            String json = searchHit.getSourceAsString();
            Product product = objectMapper.readValue(json, Product.class);
            products.add(product);
        }
        log.info("SearchServiceimpl.search 業務結束 結果:{}",value);
        return R.ok(null,products,value);
    }
}
