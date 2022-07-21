package com.example.component;

// 版权所有 © 艾科瑞特科技
// 艾科瑞特（iCREDIT）-让企业业绩长青
// 预知更多业绩长青，请与我们联系
// 联系电话：0532-88984128
// 联系邮箱：market@itruth.xin

import me.kagura.JSONQuery;
import me.kagura.JsonResult;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.codec.binary.Base64.encodeBase64;

//依赖请参照:https://icredit-code.oss-cn-hangzhou.aliyuncs.com/pom_v2.xml
public class MarketOCR {
    public static void main(String[] args) throws IOException {
        //API产品路径
        String requestUrl = "http://blicence.market.alicloudapi.com/ai_business_license";
        //阿里云APPCODE
        //String appcode = "3cfb906917264da190b5ef4ab4c6f074";
        String appcode = "";

        CloseableHttpClient httpClient = null;
        try{
            httpClient = HttpClients.createDefault();
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // 装填参数
            if (false) {
                //启用BASE64编码方式进行识别
                //内容数据类型是BASE64编码
                String imgFile = "/Users/qiush7engkeji/Desktop/3971657591688.png";
                String imgBase64 = "";
                try {
                    File file = new File(imgFile);
                    byte[] content = new byte[(int) file.length()];
                    FileInputStream finputstream = new FileInputStream(file);
                    finputstream.read(content);
                    finputstream.close();
                    imgBase64 = new String(encodeBase64(content));
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                params.add(new BasicNameValuePair("AI_BUSINESS_LICENSE_IMAGE", imgBase64));
                params.add(new BasicNameValuePair("AI_BUSINESS_LICENSE_IMAGE_TYPE", "0"));
            } else {
                //启用URL方式进行识别
                //内容数据类型是图像文件URL链接
                params.add(new BasicNameValuePair("AI_BUSINESS_LICENSE_IMAGE", "https://s4s-images.oss-cn-shanghai.aliyuncs.com/electricity/img/c90992488c414ea5b187d32080dfaeed.png"));
                params.add(new BasicNameValuePair("AI_BUSINESS_LICENSE_IMAGE_TYPE", "1"));
            }

            // 创建一个HttpGet实例
            HttpPost httpPost = new HttpPost(requestUrl);
            httpPost.addHeader("Authorization","APPCODE " + appcode);
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

            // 设置请求参数
            httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));

            // 发送GET请求
            HttpResponse execute = httpClient.execute(httpPost);

            // 获取状态码
            int statusCode = execute.getStatusLine().getStatusCode();
            System.out.println(statusCode);

            // 获取结果
            HttpEntity entity = execute.getEntity();
            String result = EntityUtils.toString(entity, "utf-8");
            JsonResult creditCode = JSONQuery.select(result, "ENTERPRISE_NAME_CH");
            System.out.println(creditCode);
            System.out.println(result);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}