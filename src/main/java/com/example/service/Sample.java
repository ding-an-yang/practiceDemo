// This file is auto-generated, don't edit it. Thanks.
package com.example.service;

import com.aliyun.ocr_api20210707.Client;
import com.aliyun.ocr_api20210707.models.RecognizeBusinessLicenseRequest;
import com.aliyun.ocr_api20210707.models.RecognizeBusinessLicenseResponse;
import com.aliyun.ocr_api20210707.models.RecognizeBusinessLicenseResponseBody;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.Common;
import com.aliyun.teautil.models.RuntimeOptions;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

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
        InputStream inputStream = new FileInputStream("/Users/qiush7engkeji/Desktop/3971657591688_.pic_hd.jpg");
        RecognizeBusinessLicenseRequest recognizeBusinessLicenseRequest = new RecognizeBusinessLicenseRequest()
                .setBody(inputStream);
        RuntimeOptions runtime = new RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            RecognizeBusinessLicenseResponseBody body = client.recognizeBusinessLicenseWithOptions(recognizeBusinessLicenseRequest, runtime).getBody();
            String data = body.getData();

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
}