package com.example.util;

import com.aspose.cells.Workbook;
import com.aspose.slides.Presentation;
import com.aspose.words.*;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.icepdf.core.util.GraphicsRenderingHints;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.List;

import static com.example.util.License.getLicense;

/**
 * @author ：yangan
 * @date ：2022/6/21 下午8:56
 * @description：次类用于文件的相关处理，如文件读取、复制、转换等。
 * @version: 1.0
 */
public class FileUtils {

    /**
     * 替换Excel模板文件内容
     * @param item 文档数据
     * @param sourceFilePath Excel模板文件路径
     * @param targetFilePath Excel生成文件路径
     * @return 替换成功返回 TRUE
     */
    public static boolean replaceExcel(Map item, String sourceFilePath, String targetFilePath) {
        boolean bool = true;
        POIFSFileSystem fs = null;
        HSSFWorkbook wbH = null;
        HSSFSheet sheetH = null;
        XSSFWorkbook wbX = null;
        XSSFSheet sheetX = null;
        Iterator rows = null;

        try {
            // xlsx  xls
            fs = new POIFSFileSystem(new FileInputStream(sourceFilePath));
            wbH = new HSSFWorkbook(fs);
            wbX = new XSSFWorkbook();
            sheetH = wbH.getSheetAt(0);
            sheetX = wbX.getSheetAt(0);
            if (sourceFilePath.endsWith(".xlsx")){
                rows = sheetX.rowIterator();
            } else {
                rows = sheetH.rowIterator();
            }
            while (rows.hasNext()) {
                HSSFRow row = (HSSFRow) rows.next();
                if (row != null) {
                    int num = row.getLastCellNum();
                    for (int i = 0; i < num; i++) {
                        HSSFCell cell = row.getCell(i);
                        if (cell != null) {
                            //cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                        }
                        if (cell == null || cell.getStringCellValue() == null) {
                            continue;
                        }
                        String value = cell.getStringCellValue();
                        if (!"".equals(value)) {
                            Set<String> keySet = item.keySet();
                            for (String text : keySet) {
                                if (value.equalsIgnoreCase(text)) {
                                    cell.setCellValue((String) item.get(text));
                                    break;
                                }
                            }
                        } else {
                            cell.setCellValue("");
                        }
                    }
                }
            }
            // 输出文件
            FileOutputStream fileOut = new FileOutputStream(targetFilePath);
            if (targetFilePath.endsWith(".xlsx")){
                wbH.write(fileOut);
            } else {
                wbX.write(fileOut);
            }
            fileOut.close();
        } catch (Exception e) {
            bool = false;
            e.printStackTrace();
        }
        return bool;
    }

    /**
     * 将多个合并PDF文件合并为一个pdf文件
     * @param pdfFilePath 合并后的PDF文件
     * @param pdfPath 需要合并的pdf文件路径
     */
    public static void mergePdf(String pdfFilePath, String[] pdfPath) {
        //pdf合并工具类
        PDFMergerUtility mergePdf = new PDFMergerUtility();
        try {
            //这是需要合并的PDF文件
            for (String s : pdfPath) {
                mergePdf.addSource(s);
            }
            //设置合并生成pdf文件名称
            mergePdf.setDestinationFileName(pdfFilePath);
            //合并pdf
            mergePdf.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param fileName 文件名称
     * @return 读取文件的内容写入map中，文件内容存放格式  ${NAME},内容
     */
    public static Map<String, String> readFileToMap(String fileName){
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        // 将占位符文件放在static目录下
        Resource resource = new ClassPathResource("static/"+fileName);
        Map<String, String> hasMap = null;
        try {
            String absolutePath = resource.getFile().getAbsolutePath();
            fis = new FileInputStream(absolutePath);
            isr = new InputStreamReader(fis, "UTF-8");
            br = new BufferedReader(isr);
            hasMap = new HashMap<>();
            String line = "";
            String[] arrs = null;
            while ((line = br.readLine()) != null) {
                arrs = line.split(",");
                hasMap.put(arrs[0],arrs[1]);
            }
            br.close();
            isr.close();
            fis.close();
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在");
        } catch (UnsupportedEncodingException e) {
            System.out.println("不支持该编码格式");
        } catch (IOException e) {
            System.out.println("读取失败");
        }
        return hasMap;
    }

    /**
     * 替换Word中的占位符
     * doc类型的word用HWPFDocument类进行解析
     * docx类型的word用XWPFDocument类进行解析
     * @param srcPath word模板数据源路径
     * @param destPath word导出路径
     * @param map 关键字键值对映射
     */
    public static void replaceWord(String srcPath, String destPath,Map<String, String> map){
        FileOutputStream out = null;
        FileInputStream input = null;
        try {
            if(srcPath != null && srcPath.indexOf(".docx") < 1) {
                input = new FileInputStream(new File(srcPath));
                HWPFDocument document = new HWPFDocument(input);
                Range range = document.getRange();
                for(Map.Entry<String, String> entry : map.entrySet()) {
                    if (entry.getValue() == null) {
                        entry.setValue("  ");
                    }
                    range.replaceText(entry.getKey(), entry.getValue());
                }
                ByteArrayOutputStream ostream = new ByteArrayOutputStream();
                out = new FileOutputStream(new File(destPath));
                document.write(out);
                out.write(ostream.toByteArray());
                out.flush();
            }else {
                XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(srcPath));
                // 替换段落中的指定文字
                Iterator<XWPFParagraph> itPara = document.getParagraphsIterator();
                while (itPara.hasNext()) {
                    XWPFParagraph paragraph = itPara.next();
                    List<XWPFRun> runs = paragraph.getRuns();
                    for (XWPFRun run : runs) {
                        String oneparaString = run.getText(run.getTextPosition());
                        if (StringUtil.isBlank(oneparaString)){
                            continue;
                        }
                        for (Map.Entry<String, String> entry :
                                map.entrySet()) {
                            oneparaString = oneparaString.replace(entry.getKey(), entry.getValue());
                        }
                        run.setText(oneparaString, 0);
                    }

                }
                ByteArrayOutputStream ostream = new ByteArrayOutputStream();
                out = new FileOutputStream(new File(destPath));
                document.write(out);
                out.write(ostream.toByteArray());
                out.flush();
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 文件复制 doc docx pdf txt 。。。
     * FileChannel 管道传输方式确保能复制格式
     * @param inFile 传入文件路径
     * @param outFile 目标文件路径
     */
    public static void copyFileByBuffer(String inFile, String outFile) {
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
                if (inChannel.read(buf) != 1){
                    //切换到读取数据模式
                    buf.flip();
                    //将缓冲区的数据写入通道中
                    outChannel.write(buf);
                    //清空缓冲区
                    buf.clear();
                }
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

    /**
     * 文件复制 doc docx pdf txt 。。。
     * FileChannel 管道传输方式确保能复制格式
     * 使用直接缓冲区实现文件的复制（内存映射文件）
     * @param inFile 传入文件路径
     * @param outFile 目标文件路径
     */
    public static void copyFileByChannel(String inFile, String outFile) {
        long start = System.currentTimeMillis();
        try {
            //获取通道
            FileChannel inChannel = FileChannel.open(Paths.get(inFile), StandardOpenOption.READ);
            FileChannel outChannel = FileChannel.open(Paths.get(outFile), StandardOpenOption.WRITE,
                    StandardOpenOption.READ, StandardOpenOption.CREATE);
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

    /**
     * 文件复制 doc docx pdf txt 。。。
     * FileChannel 管道传输方式确保能复制格式
     * 通道之间的数据传输（直接缓冲区）
     * @param inFile 传入文件路径
     * @param outFile 目标文件路径
     */
    public static void copyFileTransfer(String inFile, String outFile) {
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

    /**
     * 将word的内容转为html返回字符串，图片全部转为base64编码。
     * @param inPath
     * @return
     */
    public static String wordToHtml(String inPath) {
        // 验证License 若不验证则转化出的pdf文档会有水印产生
        if (!getLicense()) {
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

    /**
     * word 转 PDF 目前仅支持doc格式，docx转的格式存在一个问题，有待解决
     * 当前存在 bug 转出的PDF文件页码总是从 0 开始，总是为 1，2，3 形式
     * 由于aspose组件原因，Linux上转，如果出现中文变小方块，请先在Linux安装中文字体，docker同理。
     * @param files 传入文件，可以一次性转多个文件
     */
    public static void docToPdf(MultipartFile[] files){
        for (MultipartFile file:files) {
            String wordFilePath = file.getOriginalFilename();
            System.out.println(wordFilePath);
            // 验证License 若不验证则转化出的pdf文档会有水印产生
            if (!getLicense()) {
                System.out.println(wordFilePath + ",解析水印失败，请重试");
            }
            String PDFFile = "";
            try {
                long old = System.currentTimeMillis();
                //PDFFile = createPDFFile(wordFilePath);
                // 新建一个pdf文档 转出的PDF生成存放路径
                File file2 = new File("./"+wordFilePath.substring(0, wordFilePath.lastIndexOf(".")) + ".pdf");
                FileOutputStream os = new FileOutputStream(file2);
                // 判断当前的操作系统
                String system = System.getProperty("os.name");
                if (os != null && system.toLowerCase().startsWith("windows")) {//Windows操作系统
                    FontSettings.setFontsFolder("C:\\windows\\fonts", true);
                } else if (os != null && system.toLowerCase().startsWith("linux")) {//Linux操作系统
                    FontSettings.setFontsFolder("/usr/share/fonts/Windows", true);
                } else { //mac 操作系统
                    FontSettings.setFontsFolder("/Users/qiush7engkeji/Desktop/soft/Fonts", true);
                }
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
                os.close();
            } catch (Exception e) {
                System.out.println(wordFilePath + "转换失败，请重试");
                e.printStackTrace();
            }
        }
    }

    /**
     * word 转 PDF 目前仅支持doc格式，docx转的格式存在一个问题，有待解决
     * 当前存在 bug 转出的PDF文件页码总是从 0 开始，总是为 1，2，3 形式
     * 由于aspose组件原因，Linux上转，如果出现中文变小方块，请先在Linux安装中文字体，docker同理。
     * @param inputStream Word输入流
     * @param outputStream PDF输出流
     */
    public static void docToPdf(InputStream inputStream, OutputStream outputStream) {
        // 验证License 若不验证则转化出的pdf文档会有水印产生
        if (!getLicense()) {
            System.out.println(",解析水印失败，请重试");
        }
        try {
            Document doc = new Document(inputStream); //Address是将要被转化的word文档
            com.aspose.words.PdfSaveOptions pdfSaveOptions=new com.aspose.words.PdfSaveOptions();
            pdfSaveOptions.setExportDocumentStructure(true);
            // 判断当前的操作系统
            String system = System.getProperty("os.name");
            if (system.toLowerCase().startsWith("windows")) {//Windows操作系统
                FontSettings.setFontsFolder("C:\\windows\\fonts", true);
            } else if (system.toLowerCase().startsWith("linux")) {//Linux操作系统
                FontSettings.setFontsFolder("/usr/share/fonts/Windows", true);
            } else { //mac 操作系统
                FontSettings.setFontsFolder("/Users/qiush7engkeji/Desktop/soft/Fonts", true);
            }
            doc.save(outputStream,pdfSaveOptions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * excel 转 PDF
     * @param inPath excel路径
     * @param outPath 生成PDF路径
     */
    public static void excelToPdf(String inPath, String outPath) {
        if (!getLicense()) {
            return;
        }
        try {
            // 输出路径
            File pdfFile = new File(outPath);
            // 原始excel路径
            Workbook wb = new Workbook(inPath);
            com.aspose.cells.PdfSaveOptions pdfSaveOptions = new com.aspose.cells.PdfSaveOptions();
            pdfSaveOptions.setOnePagePerSheet(true);
            FileOutputStream fileOS = new FileOutputStream(pdfFile);
            // 判断当前的操作系统
            String system = System.getProperty("os.name");
            if (system.toLowerCase().startsWith("windows")) {//Windows操作系统
                FontSettings.setFontsFolder("C:\\windows\\fonts", true);
            } else if (system.toLowerCase().startsWith("linux")) {//Linux操作系统
                FontSettings.setFontsFolder("/usr/share/fonts/Windows", true);
            } else { //mac 操作系统
                FontSettings.setFontsFolder("/Users/qiush7engkeji/Desktop/soft/Fonts", true);
            }
            wb.save(fileOS, com.aspose.cells.SaveFormat.PDF);
            fileOS.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * PPT转pdf格式
     * @param inPath ppt文件路径
     * @param outPath 转出的pdf
     */
    public static void pptToPdf(String inPath, String outPath) {
        // 验证License 若不验证则转化出的pdf文档会有水印产生
        if (!getLicense()) {
            return;
        }
        try {
            File pdfFile = new File(outPath);// 输出路径
            FileOutputStream os = new FileOutputStream(pdfFile);
            //原始路径
            Presentation pres = new Presentation(inPath);
            // 判断当前的操作系统
            String system = System.getProperty("os.name");
            if (system.toLowerCase().startsWith("windows")) {//Windows操作系统
                FontSettings.setFontsFolder("C:\\windows\\fonts", true);
            } else if (system.toLowerCase().startsWith("linux")) {//Linux操作系统
                FontSettings.setFontsFolder("/usr/share/fonts/Windows", true);
            } else { //mac 操作系统
                FontSettings.setFontsFolder("/Users/qiush7engkeji/Desktop/soft/Fonts", true);
            }
            pres.save(os, com.aspose.slides.SaveFormat.Pdf);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * pdf 转图片 支持jpg png 格式
     * @param file 上传的PDF文件
     * @param imagePath 图片的生成路径
     * @param dpi 图片清晰程度，建议150以上，越大越清晰，所需要时间也越长
     */
    public static void pdfToImageFile(MultipartFile file, String imagePath, float dpi){
        PDDocument doc = null;
        ByteArrayOutputStream os = null;
        InputStream stream = null;
        OutputStream out = null;
        try {
            // 获取PDF上传流
            stream = file.getInputStream();
            // 加载解析PDF文件
            doc = PDDocument.load(stream);
            PDFRenderer pdfRenderer = new PDFRenderer(doc);
            PDPageTree pages = doc.getPages();
            int pageCount = pages.getCount();
            for (int i = 0; i < pageCount; i++) {
                BufferedImage bim = pdfRenderer.renderImageWithDPI(i, dpi);
                os = new ByteArrayOutputStream();
                ImageIO.write(bim, "jpg", os);
                byte[] dataList = os.toByteArray();
                // jpg文件转出路径
                File iamgesFile = new File(imagePath + i + ".jpg");
                if (!iamgesFile.getParentFile().exists()) {
                    // 不存在则创建父目录及子文件
                    iamgesFile.getParentFile().mkdirs();
                    iamgesFile.createNewFile();
                }
                out = Files.newOutputStream(iamgesFile.toPath());
                out.write(dataList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (doc != null) doc.close();
                if (os != null) os.close();
                if (stream != null) stream.close();
                if (out != null) out.close();
            } catch (IOException i){
                i.printStackTrace();
            }
        }
    }


    /**
     * 将指定pdf文件的首页转换为指定路径的缩略图
     * @param filePath  原文件路径，例如d:/test.pdf
     * @param imagePath 图片生成路径，例如 d:/
     * @param zoom      控制图片的清晰度0.0f以上任意，数字越大图片越清晰
     * 10 共15张图片，需要时间（s）：27.692 清晰度较高
     * 5  共15张图片，需要时间（s）：10.858 放大清晰度有损
     * 20 共15张图片，需要时间（s）：91.506 可以达到wps效果，时间太慢
     */
    public static void pdfToImagesFile(String filePath, String imagePath, float zoom) {
        org.icepdf.core.pobjects.Document document =  new org.icepdf.core.pobjects.Document();
        float rotation = 0f;;
        try {
            /* 生成图片的名字*/
            //String imageName = UUID.randomUUID().toString();
            String imageName = "img";
            document.setFile(filePath);
            // 读取pdf文件的页数
            for (int i = 0; i < document.getNumberOfPages(); i++) {
                BufferedImage img =
                        (BufferedImage) document.getPageImage(i, GraphicsRenderingHints.SCREEN,
                                org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, zoom);
                /* 设置图片后缀 png jpg*/
                Iterator iter = ImageIO.getImageWritersBySuffix("jpg");
                ImageWriter writer = (ImageWriter) iter.next();
                File outFile =
                        new File(imagePath + imageName+"-" + (i + 1) + ".jpg");
                FileOutputStream out = new FileOutputStream(outFile);
                ImageOutputStream outImage = ImageIO.createImageOutputStream(out);
                writer.setOutput(outImage);
                writer.write(new IIOImage(img, null, null));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean merge(String[] imgs, String type, String mergePic) {
        int dstHeight = 0;
        int dstWidth = 0;
        // 获取需要拼接的图片长度
        int len = imgs.length;
        // 判断长度是否大于0
        if (len < 1) {
            return false;
        }
        File[] file = new File[len];
        BufferedImage[] images = new BufferedImage[len];
        int[][] ImageArrays = new int[len][];
        for (int i = 0; i < len; i++) {
            try {
                file[i] = new File(imgs[i]);
                images[i] = ImageIO.read(file[i]);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

            int width = images[i].getWidth();
            int height = images[i].getHeight();

            // 从图片中读取RGB 像素
            ImageArrays[i] = new int[width * height];
            ImageArrays[i] = images[i].getRGB(0, 0, width, height, ImageArrays[i], 0, width);

            // 计算合并的宽度和高度
            dstWidth = dstWidth > width ? dstWidth : width;
            dstHeight += height;
        }

        // 合成图片像素
        System.out.println("宽度:" + dstWidth);
        System.out.println("高度:" + dstHeight);

        if (dstHeight < 1) {
            System.out.println("dstHeight < 1");
            return false;
        }
        // 生成新图片
        try {
            BufferedImage imageNew = new BufferedImage(dstWidth, dstHeight, BufferedImage.TYPE_INT_RGB);
            int width_i = 0;
            int height_i = 0;
            for (int i = 0; i < images.length; i++) {
                int width = images[i].getWidth();
                int height = images[i].getHeight();
                imageNew.setRGB(0, height_i, width, height, ImageArrays[i], 0, width);
                height_i += height;
            }

            File outFile = new File(mergePic);
            // 写图片，输出到硬盘
            ImageIO.write(imageNew, type, outFile);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**文件下载
     * 文件放入Response中
     */
    public static void exportFile(File file) {
        try {
            exportFile(file.getName(),new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 文件流放入Response中
     */
    public static void exportFile(String fileName,InputStream inputStream) {
        if(StringUtils.isEmpty(fileName)) throw new RuntimeException("文件名称不能为空！");
        if(inputStream == null) throw new RuntimeException("导出文件流不能为空！");
        try {
            //1、获取扩展名
            String ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletResponse response = attributes.getResponse();
            response.setCharacterEncoding("utf-8");
            String name = URLEncoder.encode(StringUtils.isEmpty(fileName)?fileName:fileName.substring(0,fileName.length()-ext.length()-1), "utf-8");
            response.setHeader("filename", name);
            response.setHeader("Access-Control-Expose-Headers", "filename");

            response.resetBuffer();
            response.setContentType("utf-8");
            response.setHeader("content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"),"ISO8859-1"));
            String mimeType = "application/pdf";
            if(StringUtils.isEmpty(mimeType)) {
                response.setContentType("applicationnd.openxmlformats-officedocument.spreadsheetml.sheet");
            } else {
                response.setContentType(mimeType);
            }
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            bis.close();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}