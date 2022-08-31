package com.example.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.*;

/**
 * @author ：yangan
 * @date ：2022/8/1 下午3:40
 * @description：
 * @version:
 */
public class BackQueryService {

    public static List<String> backQuery(String keywords){
        String host = "https://aibankid.market.alicloudapi.com";
        String path = "/kgraph/wlh/yhmc/v1";
        String method = "GET";
        String appcode = "";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("STRING", keywords);
        querys.put("PAGE_NUM", "1");
        List<String> list = new ArrayList<>();
        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            //System.out.println(response.toString());
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("状态码："+statusCode);
            //获取response的body
            String data = EntityUtils.toString(response.getEntity());
            list = getListMap(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<String> getListMap(String json){
        JSONObject jsonObject = JSON.parseObject(json);
        JSONArray listMap = jsonObject.getJSONArray("索引实体信息");
        List<String> list = new ArrayList<>();
        for (int i = 0;i<listMap.size();i++){
            JSONObject jsonObject1 = listMap.getJSONObject(i);
            Set<String> strings = jsonObject1.keySet();
            for (String next : strings) {
                String string = jsonObject1.getString(next);
                if (!"数据编码".equals(next)){// 数据编码 银行名称 银行网联号
                    list.add(next + ":" + string);
                }
            }
        }
        return list;
    }
}