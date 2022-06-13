package com.example.util;

import java.io.*;

/**
 * @author ：yangan
 * @date ：2022/6/8 下午1:57
 * @description：
 * @version:
 */
public class CombineFiles {

    /**
     * 递归遍历文件，查找符合后缀名文文件读入
     * @param file
     */
    public static void recursiveTraversalFiles(File file, BufferedOutputStream bos) throws IOException {
        if(file.isFile()){
            //获取文件名
            String fileName = file.getName();
            //截取后缀名
            String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
            if("docx".equals(suffix)){
                //判断是md文件，完成文件读入操作
                System.out.println(fileName);
                readFile(file,bos);
            }
        }else {
            File[] files = file.listFiles();
            for(File fi :files){
                //递归遍历文件
                recursiveTraversalFiles(fi,bos);
            }
        }
    }

    /**
     * 文件读入操作
     * @param file
     * @param bos
     */
    public static void readFile(File file,BufferedOutputStream bos){
        BufferedInputStream bis = null;;
        try {
            bis = new BufferedInputStream(new FileInputStream(file));
            int len =-1;
            byte[] bytes = new byte[1024];
            while((len = bis.read(bytes))!=-1){
                bos.write(bytes,0,len);
            }
            bis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) throws IOException {
        //需要递归遍历的文件夹的绝对路径
        File file = new File("/Users/qiush7engkeji/Desktop/project/ideaProject/test/test1/");
        //合并之后的文件的路径以及文件名
        File outFile = new File("/Users/qiush7engkeji/Desktop/project/ideaProject/test/test1/hebin.docx");
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outFile));

        recursiveTraversalFiles(file,bos);
        bos.close();
    }

}