package hx.com.example.rule;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author mingliang
 * @Date 2018-04-02 18:40
 */
public class Mobile {

    private static String getSoapRequest(String mobileCode) {

        StringBuilder sb = new StringBuilder();

        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                + "\n"

                + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
                + " "

                + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\""
                + " "

                + "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                + "\n"

                + "<soap:Body>" + "\n"

                + "<getMobileCodeInfo" + " "
                + "xmlns=\"http://WebXml.com.cn/\">" + "\n"

                + "<mobileCode>" + mobileCode + "</mobileCode>" + "\n"

                + "<userID></userID>" + "\n"

                + "</getMobileCodeInfo>" + "\n"

                + "</soap:Body>" + "\n"

                + "</soap:Envelope>"

        );

        return sb.toString();

    }

    private static InputStream getSoapInputStream(String mobileCode) {

        try {

            String soap = getSoapRequest(mobileCode);

            if (soap == null)

                return null;

            URL url = new URL("http://www.webxml.com.cn/WebServices/MobileCodeWS.asmx");

            URLConnection conn = url.openConnection();

            conn.setUseCaches(false);

            conn.setDoInput(true);

            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");

            conn.setRequestProperty("Content-Length", Integer.toString(soap
                    .length()));

            conn.setRequestProperty("SOAPAction",
                    "http://WebXml.com.cn/getMobileCodeInfo");

            OutputStream os = conn.getOutputStream();

            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");

            osw.write(soap);
            osw.flush();

            osw.close();

            InputStream is = conn.getInputStream();

            return is;

        } catch (Exception e) {

            e.printStackTrace();

            return null;

        }

    }

    public static String getMobileNoTrack(String mobileCode) {

        try {

            org.w3c.dom.Document document = null;

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            dbf.setNamespaceAware(true);

            InputStream is = getSoapInputStream(mobileCode);

            DocumentBuilder db = dbf.newDocumentBuilder();

            document = db.parse(is);

            NodeList nl = document
                    .getElementsByTagName("getMobileCodeInfoResult");

            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < nl.getLength(); i++) {

                org.w3c.dom.Node n = nl.item(i);

                if (n.getFirstChild().getNodeValue().equals("手机号码错误")) {

                    sb = new StringBuffer("#");

                    System.out.println("手机号码输入有误");

                    break;

                }

                sb.append(n.getFirstChild().getNodeValue() + "\n");

            }

            is.close();

            return sb.toString();

        } catch (Exception e) {

            e.printStackTrace();

            return null;

        }

    }

    public static void main(String[] args) throws IOException {
        // System.out.println(Moblie.getSoapRequest("13272303204"));
        // System.out.println(Moblie.getSoapInputStream("13226678785"));
//        System.out.println(Mobile.getMobileNoTrack("15826352365"));
//        http://www.00cha.com/114.asp?t=057755888742
        String testUrl = "http://www.00cha.com/114.asp?t=057755888742";
//        http://www.hiphop8.com/nub/13656233655.html
        Document doc = Jsoup.connect(testUrl).get();
        Elements els = doc.getElementsByClass("searchnr");
        if (null == els){
            System.out.println("改地址暂时不支持固话查询，请更换其他的查询渠道");
        }
        Element element = (Element) els.get(0).childNodes().get(2);
        System.out.println(element.text());
    }
}
