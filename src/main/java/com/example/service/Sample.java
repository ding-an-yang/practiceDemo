// This file is auto-generated, don't edit it. Thanks.
package com.example.service;

import com.aliyun.ocr_api20210707.Client;
import com.aliyun.ocr_api20210707.models.RecognizeBusinessLicenseRequest;
import com.aliyun.ocr_api20210707.models.RecognizeBusinessLicenseResponseBody;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.Common;
import com.aliyun.teautil.models.RuntimeOptions;

import com.example.model.Students;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import java.io.FileInputStream;
import java.io.InputStream;



public class Sample {

    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public static Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "ocr-api.cn-hangzhou.aliyuncs.com";
        return new Client(config);
    }

    public static RecognizeBusinessLicenseResponseBody testOcr() throws Exception {
        Client client = Sample.createClient("LTAI5tGyE7YecGQL55rCyPvj", "1d7HWXN6wSOyJY3b6KtWEfAVyRTDUd");
        //InputStream bodySyream = com.aliyun.darabonba.stream.Client.readFromFilePath("<your-file-path>");
        InputStream inputStream = new FileInputStream("/Users/qiush7engkeji/Desktop/141657786095_.pic_hd.jpg");
        RecognizeBusinessLicenseRequest recognizeBusinessLicenseRequest = new RecognizeBusinessLicenseRequest()
                .setBody(inputStream);
        RuntimeOptions runtime = new RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            RecognizeBusinessLicenseResponseBody body = client.recognizeBusinessLicenseWithOptions(recognizeBusinessLicenseRequest, runtime).getBody();
            String data = body.getData();

            //通过fromObject将json字符串翻译成JSON对象(JSONObject)
            JSONObject jsonObject = JSONObject.fromObject(data);

            //假如返回值为简单的类型，也就是单个值，使用getString("key")获取
//            String flag=jsonObject.getString("companyName");//结果就是简单的值类型
//            System.out.println("===flag值为==="+flag);
            JSONArray studentsjsonArray = jsonObject.getJSONArray("date");//返回的是非单一的json对象
//
//            //通过循环遍历数据
//            for (int i=0;i<studentsjsonArray.size();i++) {
//                //将获取的单个json字符串翻译成JSONObject
//                JSONObject a=JSONObject.fromObject(studentsjsonArray.get(i).toString());
//                //将json对象翻译成Student对象
//                Students stu = (Students) JSONObject.toBean(a, Students.class);
//                //打印出学生的姓名
//                System.out.println("=====学生姓名====="+stu.getCompanyName());
//            }

            System.out.println(data);
            return body;
        } catch (TeaException error) {
            // 如有需要，请打印 error
            Common.assertAsString(error.message);
            System.out.println(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 如有需要，请打印 error
            Common.assertAsString(error.message);
            System.out.println(error.message);
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        testOcr();
    }
}