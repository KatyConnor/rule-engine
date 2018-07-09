package hx.com.example.rule;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @Author mingliang
 * @Date 2018-04-17 10:44
 */
public class EyeCheckTest {

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect(String.format("https://www.tianyancha.com/search?key=%s","王明亮")).get();
        System.out.println(" Document = "+doc);
//        Elements els = doc.getElementsByClass("searchnr");
//        if (null == els){
//            System.out.println("查询失败！");
//        }
//        Element element = (Element) els.get(0).childNodes().get(2);
//        System.out.println( "element = "+element);
    }
}
