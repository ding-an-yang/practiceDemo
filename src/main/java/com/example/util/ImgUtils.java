package com.example.util;

import com.google.code.appengine.awt.image.BufferedImage;
import com.google.code.appengine.imageio.ImageIO;
import com.google.code.appengine.imageio.ImageWriter;
import com.google.code.appengine.imageio.stream.ImageOutputStream;

import javax.net.ssl.HttpsURLConnection;
import java.awt.*;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author ：yangan
 * @date ：2023/7/26 下午3:43
 * @description：图片处理
 * @version:
 */
public class ImgUtils {
    private static List<File> fileList = new ArrayList<>();

    public static void main(String[] args) {
        convertAllImages("/Users/qiush7engkeji/Desktop/1690508188290.jpg", "/Users/qiush7engkeji/Desktop/2.jpg");
        //fileList.add(new File("/Users/qiush7engkeji/Desktop/1690441215303.jpg"));
//        convertAllImages(fileList);
    }

    /**
     * 去除文件列表里图片的水印并替换
     *
     * @Param fileList 文件列表
     */
    public static void convertAllImages(List<File> fileList) {
        try {
            for (File file : fileList) {
                if (!file.getName().endsWith("png") && !file.getName().endsWith("jpg")) {
                    continue;
                }
                BufferedImage bi = ImageIO.read(file); //用ImageIO流读取像素块
                if (bi != null) {
                    removeWatermark(bi);
                    String formatName = file.getName().substring(file.getName().lastIndexOf(".") + 1);//生成的图片格式
                    ImageIO.write(bi, formatName, file);//用ImageIO流生成的处理图替换原图片
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从源目录获取图片处理后导出到目标目录
     *
     * @param dir     源目录
     * @param saveDir 目标目录
     */
    private static void convertAllImages(String dir, String saveDir) {
        File dirFile = new File(dir);
        File saveDirFile = new File(saveDir);
        dir = dirFile.getAbsolutePath();
        saveDir = saveDirFile.getAbsolutePath();
        loadImages(new File(dir));
        for (File file : fileList) {
            String filePath = file.getAbsolutePath();
            String dstPath = saveDir + filePath.substring(filePath.indexOf(dir) + dir.length());
            replace(file.getAbsolutePath(), dstPath);
        }
    }

    /**
     * 加载图片
     */
    private static void loadImages(File f) {
        if (f != null) {
            if (f.isDirectory()) {
                File[] fileArray = f.listFiles();
                if (fileArray != null) {
                    for (File file : fileArray) {
                        loadImages(file); //递归调用
                    }
                }
            } else {
                String name = f.getName();
                if (name.endsWith("png") || name.endsWith("jpg")) {
                    fileList.add(f);
                }
            }
        }
    }

    /**
     * 生成源图片的处理图
     *
     * @param srcFile 源图片路径
     * @param dstFile 目标图片路径
     */
    private static void replace(String srcFile, String dstFile) {
        try {
            URL http;
            if (srcFile.trim().startsWith("https")) {
                http = new URL(srcFile);
                HttpsURLConnection conn = (HttpsURLConnection) http.openConnection();
                conn.setRequestMethod("GET");
            } else if (srcFile.trim().startsWith("http")) {
                http = new URL(srcFile);
                HttpURLConnection conn = (HttpURLConnection) http.openConnection();
                conn.setRequestMethod("GET");
            } else {
                http = new File(srcFile).toURI().toURL();
            }
            BufferedImage bi = ImageIO.read(http.openStream());
            if (bi != null) {
                removeWatermark(bi);
                exportImage(bi, srcFile, dstFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 由ImageIO流生成源图片的处理图
     *
     * @param bi       ImageIO
     * @param fileName 源图片带后缀的文件名
     * @param dstFile  目标图片路径
     */
    private static void exportImage(BufferedImage bi, String fileName, String dstFile) {
        try {
            String type = fileName.substring(fileName.lastIndexOf(".") + 1);
            Iterator<ImageWriter> it = ImageIO.getImageWritersByFormatName(type);
            ImageWriter writer = it.next();
            File f = new File(dstFile);
            ImageOutputStream ios = ImageIO.createImageOutputStream(f);
            writer.setOutput(ios);
            writer.write(bi);
            bi.flush();
            ios.flush();
            ios.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 去除水印
     */
    private static void removeWatermark(BufferedImage bi) {
        Color wColor = new Color(254, 254, 254);
        Color hColor = new Color(197, 196, 191);
        //白底水印
        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                int color = bi.getRGB(i, j);
                Color oriColor = new Color(color);
                int red = oriColor.getRed();
                int greed = oriColor.getGreen();
                int blue = oriColor.getBlue();
                if (red == 254 && greed == 254 && blue == 254) {
                    continue;
                }
                if (red > 220 && greed > 180 && blue > 80) {
                    bi.setRGB(i, j, wColor.getRGB());
                }
                if (red <= 240 && greed >= 200 && blue >= 150) {
                    bi.setRGB(i, j, wColor.getRGB());
                }
            }
        }
    }
}