package com.example.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/**
 * @author ：yangan
 * @date ：2022/6/6 上午9:13
 * @description：替换Excel中的占位符
 * @version: 1.0
 */
@SuppressWarnings("unchecked")
public class ReplaceExcel {

    /**
     * 替换Excel模板文件内容
     * @param item 文档数据
     * @param sourceFilePath Excel模板文件路径
     * @param targetFilePath Excel生成文件路径
     */
    public static boolean replaceExcel(Map item, String sourceFilePath, String targetFilePath) {
        boolean bool = true;
        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(sourceFilePath));
            //HSSFWorkbook wb = new HSSFWorkbook(fs);
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.getSheetAt(0);
            Iterator rows = sheet.rowIterator();
            while (rows.hasNext()) {
                HSSFRow row = (HSSFRow) rows.next();
                if (row != null) {
                    int num = row.getLastCellNum();
                    for (int i = 0; i < num; i++) {
                        HSSFCell cell = row.getCell(i);
                        if (cell != null) {
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                        }
                        if (cell == null || cell.getStringCellValue() == null) {
                            continue;
                        }
                        String value = cell.getStringCellValue();
                        if (!"".equals(value)) {
                            Set<String> keySet = item.keySet();
                            Iterator<String> it = keySet.iterator();
                            while (it.hasNext()) {
                                String text = it.next();
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
            wb.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            bool = false;
            e.printStackTrace();
        }
        return bool;
    }

    public static void main(String[] args) {

        Map item = new HashMap();
        item.put("${year}","000");
        item.put("num1","1");
        item.put("num2","2");
        item.put("num3","3");
        item.put("num4","4");
        item.put("num5","5");
        item.put("num6","6");

        String inFile = "/Users/qiush7engkeji/Desktop/project/ideaProject/test/工作簿1.xlsx";
        String outFile = "/Users/qiush7engkeji/Desktop/project/ideaProject/test/替换.xlsx";
        replaceExcel(item,inFile,outFile);
    }

}