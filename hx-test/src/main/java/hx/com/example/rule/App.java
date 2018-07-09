package hx.com.example.rule;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ParseException {
        String s = "2018-05-04 19:57:01";
        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s);
        Date date2 = new Date();
        System.out.println( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s).compareTo(new Date()) );
        if (date1.compareTo(date2) == -1){
            System.out.println( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s).compareTo(new Date()) );
        }

//        String ss = "ad sfx";
//        System.out.println(ss.indexOf("s"));
//        System.out.println(String.valueOf(ss.charAt(ss.indexOf("s")-1)));

//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            list.add(i+"");
//        }

//        list.forEach(str -> {
//            System.out.println(str);
//        });

//        list.forEach(System.out::println);

//        List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
//
//        System.out.println("Languages which starts with J :");
////        String str = "";
//        filter(languages, :: );
//
//        System.out.println("Languages which ends with a ");
//        filter(languages, (str)->str.endsWith("a"));
//
//        System.out.println("Print all languages :");
//        filter(languages, (str)->true);
//
//        System.out.println("Print no language : ");
//        filter(languages, (str)->false);
//
//        System.out.println("Print language whose length greater than 4:");
//        filter(languages, (str)->str.length() > 4);



//        String mobile = "15826166815";  //电话号码 057755888742
//        String url = "http://www.ip138.com:8080/search.asp?action=phone&mobile=%s";
//        String telUrl = "http://www.whatchina.com/html/stel.asp?q=085188547208&show=&what=";
//        url = String.format(url, mobile);
//        String telUrl = "http://www.00cha.com/114.asp?t=057755888742";
//        Document doc = Jsoup.connect(telUrl).get();
//        Elements els = doc.getElementsByClass("searchnr");
//        Element element = (Element) els.get(0).childNodes().get(2);
//
//        System.out.println("归属地：" + element.text());
//        System.out.println("类型：" + els.get(2).text());
//        System.out.println("区号：" + els.get(3).text());
//        System.out.println("邮编：" + els.get(4).text().substring(0, 6));

//        for (int i =0; ; i++){
//            System.out.println("i = "+i);
//        }

//        long time = betweenMinute(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-04-23 11:40:56"),new Date());
//        System.out.println(time);



//        StringBuilder table = new StringBuilder();
//        table.append("SELECT column(testObjectDTO),column(testObjectDTO) FROM table(testObject)" +
//                "left table(testObject) join on param.id = param1.id where param");
//        int index = -1;
//        while ((index = table.indexOf("table(testObject)")) != -1){
//            table.replace(index,"table(testObject)".length()+index,"testObject");
//        }
//        System.out.println(table.toString());

//        String ss = "java.text.ParseException.insert";
//        System.out.println(ss.substring(0,ss.lastIndexOf(".")+1));


        // 金额 BigDecimal 测试
//        BigDecimal amount = new BigDecimal(0);
//        BigDecimal addAmount = new BigDecimal("325.26");
//        amount = amount.add(addAmount,MathContext.DECIMAL64).setScale(2);//  ,;
//        System.out.println(amount);

//        String s = "(s)";
//        System.out.println(s.substring(s.indexOf("(")+1,s.indexOf(")")));

          List<String> lists = new ArrayList<>();
          lists.forEach(v -> {
              System.out.println(v);
          });
    }

    public static void filter(List<String> names, Predicate condition) {
        names.forEach((str) ->{
            if (condition.test(str)){
                System.out.println(str + "");
            }
        });
    }

    /**
     * 计算两个时间的分钟差， date2 - date1
     * @param date1 <Date>
     * @param date2 <Date>
     * @return int
     * @throws ParseException
     */
    public static long betweenMinute(Date date1, Date date2) throws ParseException {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long result = date2.getTime() - date1.getTime();
        return result % nd % nh / nm;
    }

    public static String getDatePoor(Date endDate, Date nowDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

}
