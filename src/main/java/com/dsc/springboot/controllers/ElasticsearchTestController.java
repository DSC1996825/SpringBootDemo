package com.dsc.springboot.controllers;

import com.dsc.springboot.utils.ElasticsearchUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ElasticsearchUtil elasticsearchUtill;

    @GetMapping("/createIndex")
    public String createIndex(@RequestParam("name") String name) {
        try {
            elasticsearchUtill.createIndex(name);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "新增索引：" + name;
    }

    @GetMapping("/deleteIndex")
    public String deleteIndex(@RequestParam("name") String name) {
        try {
            elasticsearchUtill.deleteIndex(name);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "删除索引：" + name;
    }
}
