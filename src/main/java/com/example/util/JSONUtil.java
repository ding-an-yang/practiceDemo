package com.example.util;

import com.alibaba.fastjson.*;

import java.io.*;

/**
 * @author ：yangan
 * @date ：2023/7/21 上午9:42
 * @description：
 * @version:
 */
public class JSONUtil {

    /**
     * 读取当前项目中的文件
     *
     * @throws IOException
     */
    public static void readWithClassLoader() throws IOException {
        String fileName = "our-20230720.json";
        ClassLoader classLoader = JSONUtil.class.getClassLoader();
        BufferedReader reader = null;
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder content = new StringBuilder();
        String line = reader.readLine();
        int i = 0;
        while (line != null) {//line != null
//            if (line.contains("Media:")){
//                if (line.substring(6).equals("null")){
//                    System.out.println("站位符");
//                }
//            }
            if (line.contains("Explain:")) {
                System.out.println(line.substring(8));
            }
            content.append(line);
            line = reader.readLine();
            i++;
        }
        System.out.println(i);
    }

    /**
     * 读取解析JSON文件
     */
    public static void explainJsonFile() {
        File file = new File("/Users/qiush7engkeji/Desktop/myGithub/java/practiceDemo/src/main/resources/our-20230720.json");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            JSONReader reader = new JSONReader(isr);
            // 读取JSON文件
            Object object = reader.readObject();
            // JSON串转JSON数组
            JSONArray jsonArray = JSON.parseArray(object.toString());
            for (int i = 0; i < jsonArray.size(); i++) {
                // 根据关键字提取JSON串中的内容
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String Explain = jsonObject.getString("Explain");
                String ConciseExplain = jsonObject.getString("ConciseExplain");
                System.out.println(Explain + "\t" + ConciseExplain);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解析JSON数据
     * String json = "{\"name\":\"Jack\", \"age\":18}";
     * String name = jsonObject.getString("name");
     * int age = jsonObject.getIntValue("age");
     * System.out.println(name + ", " + age); //输出 Jack, 18
     */
    public static JSONObject explainJson(String json) {
        return JSON.parseObject(json);
    }

    /**
     * fastjson解析json数组
     * String json = "[{\"name\":\"Jack\", \"age\":18}, {\"name\":\"Tom\", \"age\":20}]";
     * JSONObject jsonObject = jsonArray.getJSONObject(i);
     * String name = jsonObject.getString("name");
     * int age = jsonObject.getIntValue("age");
     * System.out.println(name + ", " + age);
     *
     * @param json
     * @return
     */
    public static JSONArray explainJsonArray(String json) {
        return JSON.parseArray(json);
    }

    // todo fastjson反序列化
    /**
     * User user = new User("Jack", 18);
     * String json = JSON.toJSONString(user);
     * System.out.println(json); //输出 {"name":"Jack","age":18}
     */

    // todo fastjson解析json字符串
    /**
     * String json = "{\"name\":\"Jack\", \"age\":18}";
     * User user = JSON.parseObject(json, User.class);
     * System.out.println(user.getName() + ", " + user.getAge()); //输出 Jack, 18
     */
}