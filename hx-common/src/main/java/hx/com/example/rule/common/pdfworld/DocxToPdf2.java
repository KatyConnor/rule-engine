package hx.com.example.rule.common.pdfworld;

import org.apache.commons.collections4.MapUtils;
import org.apache.poi.xwpf.converter.core.utils.StringUtils;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author mingliang
 * @Date 2018-06-20 16:58
 */
public class DocxToPdf2 {
    protected static final Logger logger = LoggerFactory.getLogger(DocxToPdf2.class);


//    public static void main(String[] args) throws Exception{
//        String filepath = "D:/yan.docx";
//        String outpath = "D:/yan.pdf";
//
//        InputStream source = new FileInputStream(filepath);
//        OutputStream target = new FileOutputStream(outpath);
//        Map<String, String> params = new HashMap<String, String>();
//
//
//        PdfOptions options = PdfOptions.create();
//
//        wordConverterToPdf(source, target, options, params);
//    }

    public static void convertPdf(String docxFilePath,String pdfFilePath) throws Exception{
        InputStream source = new FileInputStream(docxFilePath);
        OutputStream target = new FileOutputStream(pdfFilePath);
        Map<String, String> params = new HashMap<>();


        PdfOptions options = PdfOptions.create();

        wordConverterToPdf(source, target, options, params);
    }
    /**
     * 将word文档， 转换成pdf, 中间替换掉变量
     * @param source 源为word文档， 必须为docx文档
     * @param target 目标输出
     * @param params 需要替换的变量
     * @throws Exception
     */
    public static void wordConverterToPdf(InputStream source,
                                          OutputStream target, Map<String, String> params) throws Exception {
        wordConverterToPdf(source, target, null, params);
    }

    /**
     * 将word文档， 转换成pdf, 中间替换掉变量
     * @param source 源为word文档， 必须为docx文档
     * @param target 目标输出
     * @param params 需要替换的变量
     * @param options PdfOptions.create().fontEncoding( "windows-1250" ) 或者其他
     * @throws Exception
     */
    public static void wordConverterToPdf(InputStream source, OutputStream target,
                                          PdfOptions options,
                                          Map<String, String> params) throws Exception {
        XWPFDocument doc = new XWPFDocument(source);
        paragraphReplace(doc.getParagraphs(), params);
        for (XWPFTable table : doc.getTables()) {
            for (XWPFTableRow row : table.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    paragraphReplace(cell.getParagraphs(), params);
                }
            }
        }
        PdfConverter.getInstance().convert(doc, target, options);
    }

    /** 替换段落中内容 */
    private static void paragraphReplace(List<XWPFParagraph> paragraphs, Map<String, String> params) {
        if (MapUtils.isNotEmpty(params)) {
            for (XWPFParagraph p : paragraphs){
                for (XWPFRun r : p.getRuns()){
                    String content = r.getText(r.getTextPosition());
                    logger.info(content);
                    if(StringUtils.isNotEmpty(content) && params.containsKey(content)) {
                        r.setText(params.get(content), 0);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        String filepath = "C:\\Users\\andy\\Desktop\\催收逾期还款成功数据统计接口.docx";
        String outpath = "C:\\Users\\andy\\Desktop\\催收逾期还款成功数据统计接口.pdf";
        InputStream source;
        OutputStream target;
        try {
//            source = new FileInputStream(filepath);
//            target = new FileOutputStream(outpath);
//            Map<String, String> params = new HashMap<>();
//
//            PdfOptions options = PdfOptions.create();
            convertPdf(filepath, outpath);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
