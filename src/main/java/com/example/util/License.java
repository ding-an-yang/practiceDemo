package com.example.util;

import com.example.util.FileUtils;

import java.io.InputStream;

public class License {

    /**
     * 去掉水印
     * @return 成功返回 TRUE 失败 false
     */
    public static boolean getLicense() {
        boolean result = false;
        try {
            //  license.xml应放在..\WebRoot\WEB-INF\classes路径下
            InputStream is = FileUtils.class.getClassLoader().getResourceAsStream("license.xml");
            com.aspose.cells.License aposeLic = new com.aspose.cells.License();
            aposeLic.setLicense(is);
            result = true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
