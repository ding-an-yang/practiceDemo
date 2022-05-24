package com.example.FileToPdf;

import com.aspose.cells.PdfSaveOptions;
import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;

import java.io.File;
import java.io.FileOutputStream;

import static com.example.FileToPdf.License.getExcelLicense;
import static com.example.FileToPdf.Out.outMessage;

public class ExcelToPdf {

    public static void excelToPdf(String inPath, String outPath) {
        if (!getExcelLicense()) {
            return;
        }
        try {
            long old = System.currentTimeMillis();
            // 输出路径
            File pdfFile = new File(outPath);
            // 原始excel路径
            Workbook wb = new Workbook(inPath);
            PdfSaveOptions pdfSaveOptions = new PdfSaveOptions();
            pdfSaveOptions.setOnePagePerSheet(true);
            FileOutputStream fileOS = new FileOutputStream(pdfFile);
            wb.save(fileOS, SaveFormat.PDF);
            fileOS.close();
            long now = System.currentTimeMillis();
            outMessage(inPath, outPath, now, old);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
