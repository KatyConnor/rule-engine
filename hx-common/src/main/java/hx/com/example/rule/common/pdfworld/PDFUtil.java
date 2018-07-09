package hx.com.example.rule.common.pdfworld;

import hx.com.example.rule.common.utils.DateUtils;
import hx.com.example.rule.common.utils.FileStream;
import hx.com.example.rule.common.velocity.VelocityFactory;
import org.apache.commons.cli.ParseException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import static org.apache.commons.cli.TypeHandler.createFile;

/**
 * @Author mingliang
 * @Date 2018-06-20 9:52
 */
public class PDFUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PDFUtil.class);

    private static void pdfToWorld(String pdfUrl) throws IOException, ParseException {
        PDDocument doc=PDDocument.load(new File(pdfUrl));
        int pagenumber=doc.getNumberOfPages();

        String name1 = pdfUrl.substring(0, pdfUrl.lastIndexOf("."));
        String dirName = name1;// 创建目录D:\\pdf\\a.doc
        String fileName = dirName + ".docx";// 创建文件
        createFile(fileName);
        FileOutputStream fos=new FileOutputStream(fileName);
        Writer writer=new OutputStreamWriter(fos,"UTF-8");
        PDFTextStripper stripper=new PDFTextStripper();
        stripper.setSortByPosition(true);//排序
        stripper.setWordSeparator("");//pdfbox对中文默认是用空格分隔每一个字，通过这个语句消除空格（视频是这么说的）
        stripper.setStartPage(0);//设置转换的开始页
        stripper.setEndPage(pagenumber);//设置转换的结束页
        stripper.writeText(doc,writer);
        writer.close();
        doc.close();
        System.out.println("pdf转换word成功！");
    }

    /**
     * PDF文件生成图片
     * @param pdfurl
     */
    public static List<String> PdfToImage(String pdfurl, String saveUrl){
        FileStream.existsMk(saveUrl+"/image/");  //PDF转换成HTML保存的文件夹
        Long start = System.currentTimeMillis();
        PDDocument document = null;
        File pdfFile = null;
        try{
            pdfFile = new File(pdfurl);
            document = PDDocument.load(pdfFile, (String) null);
            int size = document.getNumberOfPages();
            String pdfName = pdfFile.getName();
            List<String> images = pdfToImageName(document,pdfName.substring(0,pdfName.lastIndexOf(".")));
            LOGGER.info("PDF name : {}, pageSize : {}",pdfFile.getName(),size);
            PDFRenderer reader = new PDFRenderer(document);
            images.forEach(name ->{
                BufferedImage image = null;
                FileOutputStream out = null;
                try {
                    image = reader.renderImage(images.indexOf(name), 1.5f);
                    out = new FileOutputStream(saveUrl + "\\image/"+ name);  //生成图片,保存位置
                    ImageIO.write(image, "png", out); //使用png的清晰度
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    image = null;
                    try {
                        out.flush();
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            LOGGER.info("Reading pdf times : {}",(System.currentTimeMillis() - start)/1000);
            return images;
        }catch(IOException e) {
            LOGGER.error("Reader parse pdf to jpg error : {}", e.getMessage());
        }finally {
            try {
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Collections.EMPTY_LIST;
    }

    public static void pdfToImageHtml(String psdUrl,String saveUrl){
        FileStream.existsMk(saveUrl+"/html/");  //PDF转换成HTML保存的文件夹
        StringBuffer htmlStr = initHtml();
        StringBuilder sbBody = new StringBuilder();
        List<String> images = PdfToImage(psdUrl,saveUrl);
        String pdfName = new File(psdUrl).getName();
        String pdfHtmlName =pdfName.substring(0,pdfName.lastIndexOf(".")) +"-"+DateUtils.dateFormatNumber(new Date())+".html";
        images.forEach(name ->{
            sbBody.append("<img src=\"" + saveUrl +"/image/"+ name + "\"/>\r\n"); //将图片路径追加到网页文件里
        });
        Map<String,Object> value = new HashMap<>();
        value.put("body",sbBody.toString());
        //生成网页文件
        FileOutputStream fos = null;
        try {
            String html = VelocityFactory.evaluate(htmlStr.toString(),value);
            fos = new FileOutputStream(saveUrl+"/html/"+pdfHtmlName);
            fos.write(html.getBytes());
            LOGGER.error("create pdf html name {}",pdfName);
        } catch (IOException e) {
            LOGGER.error("Reader parse pdf to html error : {}",e.getMessage());
        } finally {
            try {
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static StringBuffer initHtml(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("<!doctype html>\r\n");
        buffer.append("<head>\r\n");
        buffer.append("<meta charset=\"UTF-8\">\r\n");
        buffer.append("</head>\r\n");
        buffer.append("<style>\r\n");
        buffer.append("img {background-color:#fff; text-align:center; width:100%; max-width:100%;margin-top:6px;}\r\n");
        buffer.append("</style>\r\n");
        buffer.append("<body style=\"background-color:gray;width: 1024px;align-content: center;margin:auto;\">\r\n");
        buffer.append("${body}");
        buffer.append("</body>\r\n");
        buffer.append("</html>");
        return buffer;
    }

    private static List<String> pdfToImageName(PDDocument document,String name){
        //遍历处理pdf附件
        int size = document.getNumberOfPages();
        List<String> images = new ArrayList<>(size);
        for(int i=0 ; i < size; i++){
            images.add(name+"_image" + "_" + i + ".jpg");
        }
        return images;
    }

    /**
     * 借用 poi 处理Word文档 为 html
     * @param wordUrl
     * @param saveUrl
     * @throws Throwable
     */
    public static void parseDocx2Html(String wordUrl,String saveUrl) throws Throwable {
        InputStream input = new FileInputStream(wordUrl);
        String suffix = wordUrl.substring(wordUrl.lastIndexOf(".")+1);// //截取文件格式名

        //实例化WordToHtmlConverter，为图片等资源文件做准备
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
        wordToHtmlConverter.setPicturesManager(new PicturesManager() {
            public String savePicture(byte[] content, PictureType pictureType, String suggestedName,
                                      float widthInches, float heightInches) {
                return suggestedName;
            }
        });

        if ("doc".equals(suffix.toLowerCase())) {
            HWPFDocument wordDocument = new HWPFDocument(input);
            wordToHtmlConverter.processDocument(wordDocument);

            //处理图片，会在同目录下生成 image/media/ 路径并保存图片
            List pics = wordDocument.getPicturesTable().getAllPictures();
            if (pics != null) {
                for (int i = 0; i < pics.size(); i++) {
                    Picture pic = (Picture) pics.get(i);
                    try {
                        pic.writeImageContent(new FileOutputStream(saveUrl +"/"+ pic.suggestFullFileName()));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // 转换
        Document htmlDocument = wordToHtmlConverter.getDocument();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(outStream);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");//编码格式
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");//是否用空白分割
        serializer.setOutputProperty(OutputKeys.METHOD, "html");//输出类型
        serializer.transform(domSource, streamResult);
        outStream.close();
        String content = new String(outStream.toByteArray());
        FileUtils.writeStringToFile(new File(saveUrl, "interface.html"), content, "utf-8");
    }

    public static void parseToHtml(String wordUrl,String saveUrl) throws IOException {
        File f = new File(wordUrl);
        if (!f.exists()) {
            System.out.println("Sorry File does not Exists!");
        } else {
            if (f.getName().endsWith(".docx") || f.getName().endsWith(".DOCX")) {
                // 1) 加载XWPFDocument及文件
                InputStream in = new FileInputStream(f);
                XWPFDocument document = new XWPFDocument(in);

                // 2) 实例化XHTML内容(这里将会把图片等文件放到生成的"word/media"目录)
                File imageFolderFile = new File(saveUrl);
                XHTMLOptions options = XHTMLOptions.create().URIResolver(
                        new FileURIResolver(imageFolderFile));
                options.setExtractor(new FileImageExtractor(imageFolderFile));
                //options.setIgnoreStylesIfUnused(false);
                //options.setFragment(true);

                // 3) 将XWPFDocument转成XHTML并生成文件
                OutputStream out = new FileOutputStream(new File(saveUrl,"/result.html"));
                XHTMLConverter.getInstance().convert(document, out, null);
            } else {
                System.out.println("Enter only MS Office 2007+ files");
            }
        }
    }


    public static void main(String[] args) {

//        File file = new File("E:/Xshell/sss\\tes\\");
//        if (file.getName().indexOf(".") == -1){
//            System.out.println("文件夹");
//        }else {
//            System.out.println("文件");
//        }

//        String filepath = "C:\\Users\\andy\\Desktop\\催收逾期还款成功数据统计接口.docx";
//        String outpath = "C:\\Users\\andy\\Desktop\\催收逾期还款成功数据统计接口.pdf";
//        PdfToImage("C:\\Users\\andy\\Desktop\\ASM4使用指南.pdf","E:\\Xshell");
//        pdfToImageHtml("C:\\Users\\andy\\Desktop\\ASM4使用指南.pdf","E:\\Xshell");


        try {
//            parseDocx2Html("C:\\Users\\andy\\Desktop\\桌面\\单身声明.doc","E:\\Xshell");
            parseToHtml("C:\\Users\\andy\\Desktop\\智能外呼平台工单任务模块接口文档v2.1.8-2.docx","E:\\Xshell");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

//        PdfToImage("C:\\Users\\andy\\Desktop\\吴峻申简历.pdf");

//        FileStream.copyFile(new File(filepath),new File(outpath));
//        InputStream source;
//        OutputStream target;
//        try {
//            source = new FileInputStream(filepath);
//            target = new FileOutputStream(outpath);
//            Map<String, String> params = new HashMap<>();
//
//            PdfOptions options = PdfOptions.create();
//            wordConverterToPdf(source, target, options, params);
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }



//        try {
//            try {
//                pdfToWorld();
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }





        //将pdf装图片 并且自定义图片得格式大小
//        File file = new File("");
//        try {
//            PDDocument doc = PDDocument.load(file);
//            PDFRenderer renderer = new PDFRenderer(doc);
//            int pageCount = doc.getNumberOfPages();
//            for (int i = 0; i < pageCount; i++) {
//                BufferedImage image = renderer.renderImageWithDPI(i, 240);
//                BufferedImage srcImage = resize(image, image.getWidth(), image.getHeight());
//                ImageIO.write(srcImage, "PNG", new File(pngPath.replace(".",i+".")));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
