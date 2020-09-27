package com.dsc.springboot.controllers;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试类
 *
 * @author DSC
 * @date 2020/9/25 10:30
 **/
@RestController
@RequestMapping("/elasticsearch")
public class ElasticsearchTestController {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @GetMapping("/test")
    public String test() {
        return "";
    }
}
