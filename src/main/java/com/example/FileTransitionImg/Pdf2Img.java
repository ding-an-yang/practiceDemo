package com.example.FileTransitionImg;

import com.example.FileToPdf.DocToPdf;
import com.ibm.icu.impl.ICUNotifier;
import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import static com.example.FileToPdf.DocToPdf.createPDFFile;
import static com.example.FileToPdf.DocToPdf.docToPdf;

public class Pdf2Img {

    public static void main(String[] args) {
        String inPath = "/Users/qiush7engkeji/Desktop/project/ideaProject/test/副本报表SQL2.pdf";
        String pngPath = "/Users/qiush7engkeji/Desktop/project/ideaProject/test/imges/";
        tranfer2(inPath, pngPath,20);

        // 10 共15张图片，需要时间（s）：27.692 清晰度较高
        // 5  共15张图片，需要时间（s）：10.858 放大清晰度有损
        // 20 共15张图片，需要时间（s）：91.506 可以达到wps效果，时间太慢
    }
    /**
     * 将指定pdf文件的首页转换为指定路径的缩略图
     * @param filepath  原文件路径，例如d:/test.pdf
     * @param imagepath 图片生成路径，例如 d:/
     * @param zoom      控制图片的清晰度0.0f以上任意，数字越大图片越清晰
     * @return 生成图片名称列表
     */
    public static void tranfer(String filepath, String imagepath, float zoom) {
        Document document =  new Document();
        DocToPdf docToPdf = new DocToPdf();
        float rotation = 0f;;
        try {
            /* 生成图片的名字*/
            //String imageName = UUID.randomUUID().toString();
            String imageName = "img";
            // pdf文件设置
            document.setFile(docToPdf.docToPdf(filepath));
            // 读取pdf文件的页数
            for (int i = 0; i < document.getNumberOfPages(); i++) {
                BufferedImage img =
                        (BufferedImage) document.getPageImage(i, GraphicsRenderingHints.SCREEN,
                                org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, zoom);
                /* 设置图片后缀 png jpg*/
                Iterator iter = ImageIO.getImageWritersBySuffix("jpg");
                ImageWriter writer = (ImageWriter) iter.next();

                /*String folderPath = imagepath+"\\"+imageName;
                File fileFolder = new File(folderPath);
                if (!fileFolder.exists() && !fileFolder.isDirectory()){
                    System.out.println("创建目录");
                    fileFolder.mkdirs();
                }*/
                File outFile =
                        new File(imagepath + imageName+"-" + (i + 1) + ".jpg");
                FileOutputStream out = new FileOutputStream(outFile);
                ImageOutputStream outImage = ImageIO.createImageOutputStream(out);
                writer.setOutput(outImage);
                writer.write(new IIOImage(img, null, null));
            }
        } catch (PDFException e) {
            e.printStackTrace();
        } catch (PDFSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void tranfer2(String filepath, String imagepath, float zoom) {
        Document document =  new Document();
        float rotation = 0f;;
        try {
            long begin = System.currentTimeMillis();
            /* 生成图片的名字*/
            //String imageName = UUID.randomUUID().toString();
            String imageName = "img";
            // pdf文件设置
            document.setFile(filepath);
            // 读取pdf文件的页数
            int count = 0;
            for (int i = 0; i < document.getNumberOfPages(); i++) {
                BufferedImage img =
                        (BufferedImage) document.getPageImage(i, GraphicsRenderingHints.SCREEN,
                                org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, zoom);
                /* 设置图片后缀 png jpg*/
                Iterator iter = ImageIO.getImageWritersBySuffix("jpg");
                ImageWriter writer = (ImageWriter) iter.next();

                /* 创建pdf同目录下的存生成图片文件夹*/
                /*File file = new File(filepath);
                String folderPath = file.getParent() + "/" + imageName;
                File fileFolder = new File(folderPath);
                if (!fileFolder.exists() && !fileFolder.isDirectory()){
                    System.out.println("创建目录");
                    fileFolder.mkdirs();
                }

                 */
                /*生成图片  .jpg .png 等*/
                File outFile =
                        new File(imagepath + imageName+"-" + (i + 1) + ".jpg");
                FileOutputStream out = new FileOutputStream(outFile);
                ImageOutputStream outImage = ImageIO.createImageOutputStream(out);
                writer.setOutput(outImage);
                writer.write(new IIOImage(img, null, null));
                ++count;
            }
            long end = System.currentTimeMillis();
            System.out.println("共" + count + "张图片，需要时间（s）：" + (end - begin) / 1000.0);
        } catch (PDFException e) {
            e.printStackTrace();
        } catch (PDFSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
