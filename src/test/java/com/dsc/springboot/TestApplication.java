package com.dsc.springboot;

import com.googlecode.aviator.AviatorEvaluator;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author DSC
 * @date 2020/9/25 10:31
 **/
public class TestApplication {
    public static void main(String[] args) {
        Map param = new HashMap();
        param.put("指标1", 1);
        param.put("指标2", 2);
        param.put("指标3", 3);
        Object result = AviatorEvaluator.execute("(指标1+指标2)*指标3", param);
        System.out.println(result);
    }
}
