package com.dsc.springboot.utils;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.ElasticsearchClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 工具类
 *
 * @author DSC
 * @date 2020/9/27 16:36
 **/
@Service
public class ElasticsearchUtil {

    @Autowired
    private RestHighLevelClient client;

    public void close() throws IOException {
        client.close();
    }

    public void createIndex(String indexName) throws IOException {
        IndexRequest indexRequest = new IndexRequest(indexName);
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        close();
    }
}
