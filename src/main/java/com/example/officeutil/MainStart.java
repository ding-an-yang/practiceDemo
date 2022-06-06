package com.example.officeutil;

import com.example.officeutil.config.JacobConfig;
import com.example.officeutil.util.Office2PdfUtil;

public class MainStart {

	public static void main(String[] ars) {
		String inFile = "/Users/qiush7engkeji/Desktop/project/ideaProject/test/电力交易合同-浙江省模板.docx";
		String outFile = "/Users/qiush7engkeji/Desktop/project/ideaProject/test/github简介.pdf";
		long time = System.currentTimeMillis();
		long endTime = 0;
		String s = System.getProperty("user.dir");
		endTime = System.currentTimeMillis();
		System.out.println(endTime - time);
		JacobConfig.configDll(s + "\\jacob-1.19-x64.dll", true);
		endTime = System.currentTimeMillis();
		System.out.println(endTime - time);
		Office2PdfUtil.office2pdf(inFile, outFile);
		Office2PdfUtil.office2pdf(inFile, outFile);
		endTime = System.currentTimeMillis();
		System.out.println(endTime - time);
	}

}
