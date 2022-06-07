package com.example.util;

import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;

/**
 * @author ：yangan
 * @date ：2022/6/2 下午3:58
 * @description：PDF的相关操作
 * @version: 1.0
 */
public class PdfMerge {
    public static void main(String[] args) {
        MergePdf();
    }
    public static void MergePdf() {
        //pdf合并工具类
        PDFMergerUtility mergePdf = new PDFMergerUtility();
        //合并pdf生成的文件名
        String destinationFileName =new Date().getTime()+".pdf";
        // 合并后pdf存放路径
        String bothPath =  "/Users/qiush7engkeji/Desktop/project/ideaProject/test/test1/PDF/";
        File file3 = new File(bothPath);
        try{
            if(!file3.exists()){
                file3.mkdirs();
            }
            //这是需要合并的PDF文件
            //mergePdf.addSource("/Users/qiush7engkeji/Desktop/project/ideaProject/test/测试用word文本.pdf");
            for (int i = 1; i < 12; i++) {
                mergePdf.addSource("/Users/qiush7engkeji/Desktop/project/ideaProject/test/test1/PDF/"+i+".pdf");
            }
            //设置合并生成pdf文件名称
            mergePdf.setDestinationFileName(bothPath + File.separator + destinationFileName);
            //合并pdf
            mergePdf.mergeDocuments();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("pdf文件合并成功");
    }

}