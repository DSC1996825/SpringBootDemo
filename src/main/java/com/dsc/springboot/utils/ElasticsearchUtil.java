package com.dsc.springboot.utils;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.open.OpenIndexRequest;
import org.elasticsearch.action.admin.indices.open.OpenIndexResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.*;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.Script;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * ElasticSearch 工具类
 *
 * @author DSC
 * @date 2020/9/27 16:36
 **/
@Service
public class ElasticsearchUtil {

    @Resource
    private RestHighLevelClient client;

    public void close() throws Exception {
        client.close();
    }

    /**
     * 新建索引
     *
     * @param indexName 索引名称
     * @author DSC
     * @date 2020/9/30 10:24
     **/
    public void getIndex(String indexName) throws Exception {
        GetIndexRequest request = new GetIndexRequest(indexName);
        GetIndexResponse response = client.indices().get(request, RequestOptions.DEFAULT);
        Map<String, MappingMetaData> mappings = response.getMappings();
    }

    /**
     * 新建索引
     *
     * @param indexName 索引名称
     * @author DSC
     * @date 2020/9/30 10:24
     **/
    public void createIndex(String indexName) throws Exception {
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
    }

    /**
     * 删除索引
     *
     * @param indexName 索引名称
     * @author DSC
     * @date 2020/9/30 10:24
     **/
    public void deleteIndex(String indexName) throws Exception {
        DeleteIndexRequest request = new DeleteIndexRequest(indexName);
        AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
    }

    /**
     * 索引是否存在
     *
     * @param indexName 索引名称
     * @author DSC
     * @date 2020/9/30 10:24
     **/
    public boolean existsIndex(String indexName) throws Exception {
        GetIndexRequest request = new GetIndexRequest(indexName);
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        return exists;
    }

    /**
     * 打开索引
     *
     * @param indexName 索引名称
     * @author DSC
     * @date 2020/9/30 10:24
     **/
    public void openIndex(String indexName) throws Exception {
        OpenIndexRequest request = new OpenIndexRequest(indexName);
        OpenIndexResponse response = client.indices().open(request, RequestOptions.DEFAULT);
    }

    /**
     * 关闭索引
     *
     * @param indexName 索引名称
     * @author DSC
     * @date 2020/9/30 10:24
     **/
    public void closeIndex(String indexName) throws Exception {
        CloseIndexRequest request = new CloseIndexRequest(indexName);
        CloseIndexResponse response = client.indices().close(request, RequestOptions.DEFAULT);
    }

    /**
     * 获取文档
     *
     * @param indexName 索引名称
     * @param id        文档ID
     * @author DSC
     * @date 2020/9/30 10:24
     **/
    public Map getDocument(String indexName, String id) throws Exception {
        GetRequest request = new GetRequest(indexName, id);
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        Map<String, Object> source = response.getSource();
        return source;
    }

    /**
     * 创建文档
     *
     * @param indexName   索引名称
     * @param id          文档ID
     * @param contentJson 文档内容json字符串
     * @author DSC
     * @date 2020/9/30 10:24
     **/
    public void createDocument(String indexName, String id, String contentJson) throws Exception {
        IndexRequest request = new IndexRequest(indexName);
        request.id(id);
        request.source(contentJson, XContentType.JSON);
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
    }

    /**
     * 文档是否存在
     *
     * @param indexName 索引名称
     * @param id        文档ID
     * @author DSC
     * @date 2020/9/30 11:20
     **/
    public boolean existsDocument(String indexName, String id) throws Exception {
        GetRequest request = new GetRequest(indexName, id);
        boolean exists = client.exists(request, RequestOptions.DEFAULT);
        return exists;
    }

    /**
     * 删除文档
     *
     * @param indexName 索引名称
     * @param id        文档ID
     * @author DSC
     * @date 2020/9/30 11:20
     **/
    public void deleteDocument(String indexName, String id) throws Exception {
        DeleteRequest request = new DeleteRequest(indexName, id);
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
    }

    /**
     * 更新文档
     *
     * @param indexName   索引名称
     * @param id          文档ID
     * @param contentJson 文档内容json字符串
     * @author DSC
     * @date 2020/9/30 11:20
     **/
    public void updateDocument(String indexName, String id, String contentJson) throws Exception {
        UpdateRequest request = new UpdateRequest(indexName, id);
        request.doc(contentJson, XContentType.JSON);
        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
    }

    /**
     * 更新查询的文档
     *
     * @param indexName   索引名称
     * @param contentJson 文档内容json字符串
     * @author DSC
     * @date 2020/9/30 11:20
     **/
    public void updateQueryDocument(String indexName, String contentJson) throws Exception {
        UpdateByQueryRequest request = new UpdateByQueryRequest(indexName);
        // 更新时版本冲突
        request.setConflicts("proceed");
        // 设置查询条件，第一个参数是字段名，第二个参数是字段的值
        request.setQuery(new TermQueryBuilder("user", "kimchy"));
//        request.setScript(new Script())
        BulkByScrollResponse response = client.updateByQuery(request, RequestOptions.DEFAULT);
    }

    /**
     * 删除查询的文档
     *
     * @param indexName   索引名称
     * @param contentJson 文档内容json字符串
     * @author DSC
     * @date 2020/9/30 11:20
     **/
    public void deleteQueryDocument(String indexName, String contentJson) throws Exception {
        DeleteByQueryRequest request = new DeleteByQueryRequest(indexName);
        // 更新时版本冲突
        request.setConflicts("proceed");
        // 设置查询条件，第一个参数是字段名，第二个参数是字段的值
        request.setQuery(new TermQueryBuilder("user", "kimchy"));
        BulkByScrollResponse response = client.deleteByQuery(request, RequestOptions.DEFAULT);
    }
}
