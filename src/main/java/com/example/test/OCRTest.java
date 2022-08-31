package com.example.test;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class OCRTest {

    private static final File BASE_DIR = new File("/Users/qiush7engkeji/Desktop/");
    private static final File DOCX_FILE = new File(BASE_DIR, "峰谷价附件2的副本.docx");
    private static final File DOCX_FILE_NEW = new File(BASE_DIR, "test.docx");
    private static final File PIC_FILE = new File(BASE_DIR, "WechatIMG161.jpeg");

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        InputStream is = new FileInputStream(DOCX_FILE);
        XWPFDocument docx = new XWPFDocument(is);
        List<XWPFParagraph> paragraphs = docx.getParagraphs();

        InputStream pic_is = new FileInputStream(PIC_FILE);
        BufferedImage image = ImageIO.read(PIC_FILE);

        XWPFParagraph paragraph = null;
        List<XWPFRun> runs = null;
        XWPFRun run = null;
        XWPFRun prevRun = null;
        int runPos = -1;

        for (int i = 0, size1 = paragraphs.size(); i < size1; ++i) {
            paragraph = paragraphs.get(i);
            runs = paragraph.getRuns();
            boolean found = false;

            for (int j = 0, size2 = runs.size(); j < size2; ++j) {
                run = runs.get(j);
                if ("${applyName}".equals(run.text())) {
                    found = true;
                    prevRun = (j == 0 ? null : runs.get(j - 1));
                    runPos = j;
                    break;
                }
            }

            if (found)
                break;
        }

        if (runPos >= 0) {
            if (prevRun == null)
                prevRun = paragraph.createRun();

            prevRun.addPicture(pic_is, org.apache.poi.xwpf.usermodel.Document.PICTURE_TYPE_PNG, "",
                    Units.pixelToEMU(image.getWidth()), Units.pixelToEMU(image.getHeight()));
            paragraph.removeRun(runPos);

            OutputStream os = new FileOutputStream(DOCX_FILE_NEW);
            docx.write(os);
            os.close();
        }

        docx.close();
        is.close();
    }
}