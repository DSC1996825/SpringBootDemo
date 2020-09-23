package com.dsc.springboot.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dsc.springboot.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试Redis
 *
 * @author DSC
 * @date 2020/9/23 16:47
 **/
@RestController
@RequestMapping("/redis")
public class RedisTestController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/add")
    public String add(@RequestBody String data) {
        try {
            JSONObject jsonObject = JSON.parseObject(data);
            String key = jsonObject.getString("key");
            Object value = jsonObject.get("value");
            if (StringUtils.isEmpty(key)) {
                throw new Exception("缺少key");
            }
            if (value instanceof JSONArray) {
                for (Object o : (JSONArray) value) {
                    redisUtil.lPush(key, o);
                }
            } else {
                redisUtil.set(key, value);
            }
            return ("true：" + JSON.toJSONString(redisUtil.get(key)));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ("false：" + e.getMessage());
        }
    }

    @PostMapping("/remove")
    public String remove(@RequestBody String data) {
        try {
            JSONObject jsonObject = JSON.parseObject(data);
            String key = jsonObject.getString("key");
            redisUtil.remove(key);
            return "true：" + key;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "false：" + e.getMessage();
        }
    }
}
