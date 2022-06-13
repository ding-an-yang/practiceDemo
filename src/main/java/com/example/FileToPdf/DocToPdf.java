package com.example.FileToPdf;

import com.aspose.words.*;

import com.example.model.Placeholder;
import com.example.model.Placeholder2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

import static com.example.FileToPdf.License.getWordLicense;
import static com.example.util.ReadFileUtil.readFileToMap;
import static com.example.util.ReplaceWord.replaceWord;

/**
 * word文档转pdf格式
 */
public class DocToPdf {

    // 设置目标文件的映射路径
    @Value("classpath:static/Fonts/")
    private static Resource fontsPath;

    public static String docToPdf(MultipartFile[] files){
        for (MultipartFile file:files) {
            String wordFilePath = file.getOriginalFilename();
            System.out.println(wordFilePath);
            // 验证License 若不验证则转化出的pdf文档会有水印产生
            if (!getWordLicense()) {
                System.out.println(wordFilePath + ",解析水印失败，请重试");
                return "";
            }
            String PDFFile = "";
            try {
                long old = System.currentTimeMillis();
                //PDFFile = createPDFFile(wordFilePath);
                // 新建一个pdf文档
  //              File file2 = new File(wordFilePath.substring(0, wordFilePath.lastIndexOf(".")) + ".pdf");
              File file2 = new File("/Users/qiush7engkeji/Desktop/project/ideaProject/test/test1/Mac测试.pdf");
                FileOutputStream os = new FileOutputStream(file2);
                // 验证License 是将要被转化的word文档
                Document doc = new Document(file.getInputStream());
                // 处理Word中的表格数据
                TableCollection tables = doc.getFirstSection().getBody().getTables();
                for (Table table : tables) {
                    RowCollection rows = table.getRows();
                    table.setAllowAutoFit(false);
                    for (Row row : rows) {
                        CellCollection cells = row.getCells();
                        for (Cell cell : cells) {
                            CellFormat cellFormat = cell.getCellFormat();
                            cellFormat.setFitText(false);
                            cellFormat.setWrapText(true);
                        }
                    }
                }
                // 存为PDF格式
                doc.save(os, SaveFormat.PDF);
                long now = System.currentTimeMillis();
                os.close();
                //outMessage(wordFilePath, PDFFile + ".pdf", now, old);
                //outMessage(wordFilePath, wordFilePath.substring(0, wordFilePath.lastIndexOf(".")-1)  + ".pdf", now, old);
            } catch (Exception e) {
                System.out.println(wordFilePath + "转换失败，请重试");
                e.printStackTrace();
            }
            return PDFFile + ".pdf";
        }
        return null;
    }

    public static String docToPdf2(String  wordFilePath){
            // 验证License 若不验证则转化出的pdf文档会有水印产生
            if (!getWordLicense()) {
                System.out.println(wordFilePath + ",解析水印失败，请重试");
                return "";
            }
            String PDFFile = "";
            try {
                long old = System.currentTimeMillis();
                PDFFile = createPDFFile(wordFilePath);
                // 新建一个pdf文档
                File file = new File(PDFFile+".pdf");
                FileOutputStream os = new FileOutputStream(file);
                // 验证License 是将要被转化的word文档
                Document doc = new Document(wordFilePath);
                // 处理Word中的表格数据
                TableCollection tables = doc.getFirstSection().getBody().getTables();
                for (Table table : tables) {
                    RowCollection rows = table.getRows();
                    table.setAllowAutoFit(false);
                    for (Row row : rows) {
                        CellCollection cells = row.getCells();
                        for (Cell cell : cells) {
                            CellFormat cellFormat = cell.getCellFormat();
                            cellFormat.setFitText(false);
                            cellFormat.setWrapText(true);
                        }
                    }
                }
                //FontSettings.setFontsFolder(fontsPath+File.separator, true);
                // 存为PDF格式
                doc.save(os, SaveFormat.PDF);
                long now = System.currentTimeMillis();
                os.close();
                //outMessage(wordFilePath, PDFFile + ".pdf", now, old);
                //outMessage(wordFilePath, wordFilePath.substring(0, wordFilePath.lastIndexOf(".")-1)  + ".pdf", now, old);
            } catch (Exception e) {
                System.out.println(wordFilePath + "转换失败，请重试");
                e.printStackTrace();
            }
            return PDFFile + ".pdf";
    }


    /**
     *  根据word路径创建同目录下的pdf文件
     * @param wordFile
     * @return
     * @throws IOException
     */
    public static String createPDFFile(String wordFile) {
        File file = new File(wordFile);
        //判断是否为文件夹
        if (file.isDirectory()) {
            System.out.println("请传入文件");
        }
        String parent = file.getParent();
        String name = file.getName();
        name = name.substring(0, name.lastIndexOf("."));
        return parent + "/" + name;
    }

    /**
     * 将word的内容转为html返回字符串，图片全部转为base64编码。
     *
     * @param inPath
     * @return
     */
    public static String wordToHtml(String inPath) {
        // 验证License 若不验证则转化出的pdf文档会有水印产生
        if (!getWordLicense()) {
            System.out.println(inPath + ",解析水印失败，请重试");
            return "";
        }
        ByteArrayOutputStream htmlStream = new ByteArrayOutputStream();
        String htmlText = "";
        try {
            Document doc = new Document(inPath);
            HtmlSaveOptions opts = new HtmlSaveOptions(SaveFormat.HTML);
            opts.setExportXhtmlTransitional(true);
            opts.setExportImagesAsBase64(true);
            opts.setExportPageSetup(true);
            doc.save(htmlStream, opts);
            htmlText = new String(htmlStream.toByteArray(), StandardCharsets.UTF_8);
            htmlStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return htmlText;
    }

    public static void main(String[] args) throws Exception {
        String inFile = "/Users/qiush7engkeji/Desktop/project/ideaProject/test/test1/字符填充模板doc/1.docx";
        String outFile = "/Users/qiush7engkeji/Desktop/project/ideaProject/test/test1/字符填充模板doc/替换.doc";
        //transfer(inFile, outFile);
        //copyFile("/Users/qiush7engkeji/Desktop/project/ideaProject/test/read.pdf", outFile);
        //docToPdf2(inFile);
        //setPDF(outFile);
        //readLineByBufferedReader(outFile);

        Map<String, String> map = readFileToMap("替换内容存放文件.txt");
        //replaceWord(inFile,outFile,map);

        //spireForTableOfDoc(inFile);

        Placeholder placeholder2 = new Placeholder();
        readModel(placeholder2);
    }

    /**
     * java中遍历实体类，获取属性名和属性值封装进map中
     * @param model 传入的实体类对象 new Model()
     * @return 返回封装后的map
     */
    public static Map<String,String> readModel(Object model){
        Map<String,String> hasMap = new HashMap<>();
        BigDecimal bg = null;
        if (model == null){
            return null;
        }
        try {
            for (Field field : model.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                // 可能出行类型转换异常 field.get(model).toString()
                if (field.get(model) != null){
                    if (field.get(model) instanceof Double){
                        bg = new BigDecimal(String.valueOf(field.get(model)));
                        System.out.println(field.getName()+"\t"+bg.toString());
                        hasMap.put(field.getName(),(field.get(model).toString()));
                    }else {
                        System.out.println(field.getName()+"\t"+field.get(model).toString());
                        hasMap.put(field.getName(),(field.get(model).toString()));
                    }
                }
            }
        }catch (IllegalAccessException i){
            System.out.println("类型转换异常，请检查出入数据是否正确！");
        }
        return hasMap;
    }

    public static void wordTopdf(String inFile, String outFile){
        //实例化Document类的对象
        com.spire.doc.Document doc = new com.spire.doc.Document();
        //加载Word
        doc.loadFromFile(inFile);
        //保存为PDF格式
        doc.saveToFile(outFile, com.spire.doc.FileFormat.PDF);
    }

    public static void copyFile(String inFile, String outFile) {
        long start = System.currentTimeMillis();
        FileChannel outChannel = null;
        FileChannel inChannel = null;
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(inFile);
            fos = new FileOutputStream(outFile);
            //1. 获取通道
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();
            //分配指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);
            //将通道中的数据存入缓冲区
            while (inChannel.read(buf) != -1) {
                //切换到读取数据模式
                buf.flip();
//将缓冲区的数据写入通道中
                outChannel.write(buf);
//清空缓冲区
                buf.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("耗费时间：" + (System.currentTimeMillis() - start));
    }
    //使用直接缓冲区实现文件的复制（内存映射文件）
    public static void copyFileByChannel() {
        long start = System.currentTimeMillis();
        try {
//获取通道
            FileChannel inChannel = FileChannel.open(Paths.get("e:\\demo\\Java网络编程(第4版).pdf"), StandardOpenOption.READ);
            FileChannel outChannel = FileChannel.open(Paths.get("e:\\demo\\Java网络编程(第4版copy).pdf"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);
//内存映射文件
            MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
            MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
//直接对缓冲区进行数据的读写操作
            byte[] dst = new byte[inMappedBuf.limit()];
            inMappedBuf.get(dst);
            outMappedBuf.put(dst);
            inChannel.close();
            outChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("耗费时间：" + (System.currentTimeMillis() - start));
    }

    //通道之间的数据传输（直接缓冲区）
    public static void transfer(String inFile, String outFile) {
        long start = System.currentTimeMillis();
        try {
            FileChannel inChannel = FileChannel.open(Paths.get(inFile), StandardOpenOption.READ);
            FileChannel outChannel = FileChannel.open(Paths.get(outFile), StandardOpenOption.READ,
                    StandardOpenOption.WRITE, StandardOpenOption.CREATE);
            inChannel.transferTo(0, inChannel.size(), outChannel);
            inChannel.close();
            outChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("耗费时间：" + (System.currentTimeMillis() - start));
    }

}
