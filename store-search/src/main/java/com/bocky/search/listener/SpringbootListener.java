package com.bocky.search.listener;

import com.alibaba.nacos.common.http.param.Query;
import com.bocky.client.ProductClient;
import com.bocky.doc.ProductDoc;
import com.bocky.pojo.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 　@Author: Bocky
 * 　@Date: 2023-04-26-下午 11:14
 * 　@Description: 監聽啟動並同步es
 *
 */
@Component
@Slf4j
public class SpringbootListener implements ApplicationRunner {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Resource
    private ProductClient productClient;


    private String createIndex = "{\n" +
            "  \"mappings\": {\n" +
            "    \"properties\": {\n" +
            "      \"productId\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"productName\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_smart\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"categoryId\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"productTitle\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_smart\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"productIntro\":{\n" +
            "        \"type\":\"text\",\n" +
            "        \"analyzer\": \"ik_smart\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"productPicture\":{\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"productPrice\":{\n" +
            "        \"type\": \"double\",\n" +
            "        \"index\": true\n" +
            "      },\n" +
            "      \"productSellingPrice\":{\n" +
            "        \"type\": \"double\"\n" +
            "      },\n" +
            "      \"productNum\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"productSales\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"all\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";
    /**
     * 完成es的同步
     * @param args incoming application arguments
     * 1.判斷es的product索引有沒有存在
     * 2.不存在，Java創建一個
     * 3.存在就刪除
     * 4.收尋數據
     * 5.插入
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        //1.判斷es的product索引有沒有存在
        GetIndexRequest getRequest = new GetIndexRequest("product");
        boolean exists = restHighLevelClient.indices().exists(getRequest, RequestOptions.DEFAULT);
        if (exists){
            //3.存在就刪除
            DeleteByQueryRequest dele = new DeleteByQueryRequest("product");
            dele.setQuery(QueryBuilders.matchAllQuery());//全部刪除
            restHighLevelClient.deleteByQuery(dele, RequestOptions.DEFAULT);
        }else {
            //2.不存在，創建索引表
            CreateIndexRequest createIndexRequest = new CreateIndexRequest("product");
            createIndexRequest.source(createIndex, XContentType.JSON);
            restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        }
        // 4.收尋數據
        List<Product> alllist = productClient.alllist();
        //5.插入
        BulkRequest bulkRequest = new BulkRequest();

        ObjectMapper objectMapper = new ObjectMapper();
        for (Product product : alllist) {
            ProductDoc productDoc = new ProductDoc(product);
            //用於插入數據
            IndexRequest indexRequest = new IndexRequest("product").id(product.getProductId().toString());
            //轉json
            String json = objectMapper.writeValueAsString(productDoc);
            indexRequest.source(json, XContentType.JSON);
            bulkRequest.add(indexRequest);
        }

        restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
    }
}
