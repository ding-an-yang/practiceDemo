package com.example.FileToPdf;

import com.aspose.words.*;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.FileToPdf.License.getWordLicense;
import static com.example.FileToPdf.Out.outMessage;
import static com.example.util.ReadFileUtil.readFileToMap;
import static com.example.util.ReadFileUtil.spireForTableOfDoc;
import static com.example.util.ReplaceWord.replaceWord;

/**
 * word文档转pdf格式
 */
public class DocToPdf {


    public static String docToPdf(String wordFilePath) throws Exception {
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
            File file = new File(PDFFile + ".pdf");
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
            doc.save(os, SaveFormat.PDF);

            // 全面支持DOC, DOCX,
            // OOXML, RTF HTML,
            // OpenDocument,
            // PDF, EPUB, XPS,
            // SWF 相互转换
            long now = System.currentTimeMillis();
            os.close();
            outMessage(wordFilePath, PDFFile + ".pdf", now, old);
        } catch (Exception e) {
            System.out.println(wordFilePath + "转换失败，请重试");
            e.printStackTrace();
        }
        return PDFFile + ".pdf";
    }

    public static void setPDF(String pdfFilePath) throws Exception {
        com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
        try {
            FileOutputStream fos = new FileOutputStream(pdfFilePath);
            PdfWriter.getInstance(doc, fos);
            doc.open();
            // 将段落行距设置为32
            com.itextpdf.text.Paragraph para1 = new com.itextpdf.text.Paragraph(10);
            // 在段落之前和之后设置空间
            para1.setSpacingBefore(10);
            para1.setSpacingAfter(10);
            doc.add(new Chunk(""));
            doc.add(para1);
            doc.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
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
        String inFile = "/Users/qiush7engkeji/Desktop/project/ideaProject/test/test1/2.docx";
        String outFile = "/Users/qiush7engkeji/Desktop/project/ideaProject/test/替换.docx";
        //transfer(inFile, outFile);
        //copyFile("/Users/qiush7engkeji/Desktop/project/ideaProject/test/read.pdf", outFile);
        docToPdf(inFile);
        //setPDF(outFile);
        //readLineByBufferedReader(outFile);

        /*Map<String, String> map = readFileToMap("替换内容存放文件.txt");
        replaceWord(inFile,outFile,map);*/

        //spireForTableOfDoc(inFile);

    }
    
    //java中遍历实体类，获取属性名和属性值
    public static Map<String,String> testReflect(Object model) throws Exception{
        Map<String,String> hasMap = new HashMap<>();
        if (model == null){
            return null;
        }
        for (Field field : model.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            hasMap.put(field.getName(),(field.get(model).toString()));
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
