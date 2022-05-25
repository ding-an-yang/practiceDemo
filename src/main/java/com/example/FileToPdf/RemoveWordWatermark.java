package com.example.FileToPdf;


import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import com.aspose.words.Document;
import com.aspose.words.HeaderFooter;
import com.aspose.words.HeaderFooterType;
import com.aspose.words.HorizontalAlignment;
import com.aspose.words.License;
import com.aspose.words.Paragraph;
import com.aspose.words.RelativeHorizontalPosition;
import com.aspose.words.RelativeVerticalPosition;
import com.aspose.words.Section;
import com.aspose.words.Shape;
import com.aspose.words.ShapeType;
import com.aspose.words.VerticalAlignment;
import com.aspose.words.WrapType;

import static com.example.FileToPdf.License.getPptLicense;

/**
 * @author ShengYoufu
 *
 */
public class RemoveWordWatermark {

    public static void main(String[] args) throws Exception {
        // Sample infrastructure.
//      URI exeDir = RemoveWordWatermark.class.getResource("").toURI();
//      String dataDir = new File(exeDir.resolve("../../Data")) + File.separator;
//      Document doc = new Document(dataDir + "TestFile.doc");
//      insertWatermarkText(doc, "CONFIDENTIAL");
//      doc.save(dataDir + "TestFile Out.doc");
        getPptLicense();
        Document doc = new Document("/Users/qiush7engkeji/Desktop/project/ideaProject/test/test的副本.docx");
        removeWatermark(doc);
        doc.save("/Users/qiush7engkeji/Desktop/project/ideaProject/test/test的副本2.docx");
    }

    /**
     *
     * Inserts a watermark into a document.
     *
     * @param doc The input document.
     * @param watermarkText Text of the watermark.
     *
     */
    private static void insertWatermarkText(Document doc, String watermarkText) throws Exception {
        // Create a watermark shape. This will be a WordArt shape.
        // You are free to try other shape types as watermarks.
        Shape watermark = new Shape(doc, ShapeType.TEXT_PLAIN_TEXT);
        // Set up the text of the watermark.
        watermark.getTextPath().setText(watermarkText);
        watermark.getTextPath().setFontFamily("Arial");
        watermark.setWidth(500);
        watermark.setHeight(100);
        // Text will be directed from the bottom-left to the top-right corner.
        watermark.setRotation(-40);
        // Remove the following two lines if you need a solid black text.
        watermark.getFill().setColor(Color.GRAY); // Try LightGray to get more Word-style watermark
        watermark.setStrokeColor(Color.GRAY); // Try LightGray to get more Word-style watermark
        // Place the watermark in the page center.
        watermark.setRelativeHorizontalPosition(RelativeHorizontalPosition.PAGE);
        watermark.setRelativeVerticalPosition(RelativeVerticalPosition.PAGE);
        watermark.setWrapType(WrapType.NONE);
        watermark.setVerticalAlignment(VerticalAlignment.CENTER);
        watermark.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // Create a new paragraph and append the watermark to this paragraph.
        Paragraph watermarkPara = new Paragraph(doc);
        watermarkPara.appendChild(watermark);
        // Insert the watermark into all headers of each document section.
        for (Section sect : doc.getSections()) {
            // There could be up to three different headers in each section, since we want
            // the watermark to appear on all pages, insert into all headers.
            insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_PRIMARY);
            insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_FIRST);
            insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_EVEN);
        }
    }

    /**
     * 插入水印
     * @param watermarkPara
     * @param sect
     * @param headerType
     * @throws Exception
     */
    private static void insertWatermarkIntoHeader(Paragraph watermarkPara, Section sect, int headerType) throws Exception {
        HeaderFooter header = sect.getHeadersFooters().getByHeaderFooterType(headerType);
        if (header == null) {
            // There is no header of the specified type in the current section, create it.
            header = new HeaderFooter(sect.getDocument(), headerType);
            sect.getHeadersFooters().add(header);
        }

        // Insert a clone of the watermark into the header.
        header.appendChild(watermarkPara.deepClone(true));
    }

    /**
     * 移除全部水印
     * @param doc
     * @throws Exception
     */
    private static void removeWatermark(Document doc) throws Exception {
        for (Section sect : doc.getSections()) {
            // There could be up to three different headers in each section, since we want
            // the watermark to appear on all pages, insert into all headers.
            removeWatermarkFromHeader(sect, HeaderFooterType.HEADER_EVEN);
            removeWatermarkFromHeader(sect, HeaderFooterType.HEADER_PRIMARY);
            removeWatermarkFromHeader(sect, HeaderFooterType.FOOTER_EVEN);
            removeWatermarkFromHeader(sect, HeaderFooterType.FOOTER_PRIMARY);
            removeWatermarkFromHeader(sect, HeaderFooterType.HEADER_FIRST);
            removeWatermarkFromHeader(sect, HeaderFooterType.FOOTER_FIRST);
        }
    }

    /**
     * 移除指定Section的水印
     * @param sect
     * @param headerType
     * @throws Exception
     */
    private static void removeWatermarkFromHeader(Section sect, int headerType) throws Exception {
        HeaderFooter header = sect.getHeadersFooters().getByHeaderFooterType(headerType);
        if (header != null) {
            header.removeAllChildren();
        }
    }

}