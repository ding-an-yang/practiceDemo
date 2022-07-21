// This file is auto-generated, don't edit it. Thanks.
package com.example.component;

import com.aliyun.ocr_api20210707.Client;
import com.aliyun.ocr_api20210707.models.RecognizeBusinessLicenseRequest;
import com.aliyun.ocr_api20210707.models.RecognizeBusinessLicenseResponseBody;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.Common;
import com.aliyun.teautil.models.RuntimeOptions;

import com.example.model.Students;
import me.kagura.JSONQuery;
import me.kagura.JsonResult;
import me.kagura.exception.FieldNotExistException;
import me.kagura.exception.TypeNotMismatchException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;



public class AliOCRClinent {

    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public static Client createClient(String accessKeyId, String accessKeySecret){
        Config config = new Config()
                // 您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "ocr-api.cn-hangzhou.aliyuncs.com";
        try {
            return new Client(config);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static RecognizeBusinessLicenseResponseBody testOcr() throws FileNotFoundException {
        //Client client = AliOCRClinent.createClient("LTAI5tGyE7YecGQL55rCyPvj", "1d7HWXN6wSOyJY3b6KtWEfAVyRTDUd");
        Client client = AliOCRClinent.createClient("", "");
        //InputStream bodySyream = com.aliyun.darabonba.stream.Client.readFromFilePath("<your-file-path>");
        InputStream inputStream = new FileInputStream(new File("https://s4s-images.oss-cn-shanghai.aliyuncs.com/electricity/img/eeed453c6c27452384a52cbac4e9b063.jpg"));
//        InputStream inputStream2 = new InputStream();
        RecognizeBusinessLicenseRequest recognizeBusinessLicenseRequest = new RecognizeBusinessLicenseRequest()
                .setBody(inputStream);
        RuntimeOptions runtime = new RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            RecognizeBusinessLicenseResponseBody body = client.recognizeBusinessLicenseWithOptions(recognizeBusinessLicenseRequest, runtime).getBody();
            String data = body.getData();

            JsonResult creditCode = JSONQuery.select(data, "data > creditCode");
            System.out.println("creditCode:"+creditCode);
            JsonResult companyName = JSONQuery.select(data, "data > companyName");
            System.out.println("companyName:"+companyName);
            JsonResult legalPerson = JSONQuery.select(data, "data > legalPerson");
            System.out.println("legalPerson:"+legalPerson);
            JsonResult businessAddress = JSONQuery.select(data, "data > businessAddress");
            Students s = new Students();
            s.setCompanyName(companyName.toString());
            System.out.println("businessAddress:"+businessAddress);
            System.out.println(s.getCompanyName());

            return body;
        } catch (TeaException error) {
            // 如有需要，请打印 error
            Common.assertAsString(error.message);
            // 没有购买阿里服务或者服务过期了
            System.out.println(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 如有需要，请打印 error
            Common.assertAsString(error.message);
            // 一般为JSON格式解析异常
            System.out.println(error.message);
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        //Test();
        testOcr();

    }


    public static void Test() throws TypeNotMismatchException, FieldNotExistException {
        String json = "" +
                "{\n" +
                "  \"errno\": 0,\n" +
                "  \"errmsg\": 成功,\n" +
                "  \"user\": \"{\\\"user_id\\\":643361255,\\\"user_name\\\":\\\"鹞之神乐\\\",\\\"user_sex\\\":1,\\\"user_status\\\":1}\",\n" +
                "  \"comment_info\": [\n" +
                "    {\n" +
                "      \"tid\": \"5504460056\",\n" +
                "      \"pid\": \"116776960983\",\n" +
                "      \"cid\": \"116857893053\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"tid\": \"5504460056\",\n" +
                "      \"pid\": \"116776960983\",\n" +
                "      \"cid\": \"116858057626\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"tid\": \"5504460056\",\n" +
                "      \"pid\": \"116776960983\",\n" +
                "      \"cid\": \"116880757453\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"data\": {\n" +
                "    \"comment_list\": {\n" +
                "      \"116776891765\": {\n" +
                "        \"comment_num\": 3,\n" +
                "        \"comment_list_num\": 4\n" +
                "      },\n" +
                "      \"116776960983\": {\n" +
                "        \"comment_num\": 4,\n" +
                "        \"comment_list_num\": 4\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";

        //获取根元素errno
        JsonResult jsonResult = JSONQuery.select(json, "errno");
        //获取根元素errno，并转换为int
        int errno = jsonResult.getAsInt();
        //获取根元素data中的comment_list
        jsonResult = JSONQuery.select(json, "data > comment_list");
        //正则过滤出属性数组，针对一部分拿对象当数组用的情况
        jsonResult = JSONQuery.select(json, "data > comment_list > [\\d+]");
        //获取数组指定位置的元素
        jsonResult = JSONQuery.select(json, "comment_info > [2]");
        //获取数组指定位置的元素 负数坐标
        jsonResult = JSONQuery.select(json, "comment_info > [-1]");
        //针对某个字符串属性的值又是个json字符串的情况
        jsonResult = JSONQuery.select(json, "user > user_name");
        //jsonResult作为参数替代json字符串
        JsonResult data = JSONQuery.select(json, "data");
        jsonResult = JSONQuery.select(data, "comment_list");
        //将json字符串转换为JsonResult
        jsonResult = JSONQuery.select(json, "");
        jsonResult = JSONQuery.select(json, null);

//        // v0.2.5新增
//        //将选择结果反序列化为普通对象
//        Post post = JSONQuery.select(json, "comment_info > [2]", Post.class);
//        //将选择结果反序列化为普通对象数组
//        Post[] postArray = JSONQuery.select(json, "comment_info", Post[].class);
//        //将选择结果反射为泛型类型List<Post>
//        Type type = new TypeToken<List<Post>>() {}.getType();
//        List<Post> postList = JSONQuery.select(json, "comment_info", type);
        System.out.println(JSONQuery.select(json, "data > comment_list > 116776891765 > comment_num"));
    }

}