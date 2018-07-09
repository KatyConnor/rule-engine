package hx.com.example.rule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author mingliang
 * @Date 2018-04-02 17:24
 */
public class PhoneTest {

    /**

     * @param args

     */

    public static void main(String[] args) {

//        String url = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=18696865250";
//
//        String json = loadJSON(url);
//
//        System.out.println(json);
        try {
            calcMobileCity("057755888742");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }



    public static String loadJSON (String url) {

        StringBuilder json = new StringBuilder();

        try {

            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine = null;

            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {

        } catch (IOException e) {

        }

        return json.toString();
    }


    public static String calcMobileCity(String mobileNumber) throws MalformedURLException {

        //获取拍拍网的API地址
        //        String urlString = "http://virtual.paipai.com/extinfo/GetMobileProductInfo?mobile="
        //                + mobileNumber + "&amount=10000&callname=getPhoneNumInfoExtCallback";
        //淘宝网的API地址
        String urlString = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel="
                + mobileNumber;

//        String urlString  =  "http://www.0512s.com/c/bd.asp?telno="+ mobileNumber;

        StringBuffer sb = new StringBuffer();
        BufferedReader buffer;
        URL url = new URL(urlString);
        String province = "";
        try {
            //获取URL地址中的页面内容
            InputStream in = url.openStream();
            // 解决乱码问题
            buffer = new BufferedReader(new InputStreamReader(in, "gb2312"));
            String line = null;
            //一行一行的读取数据
            while ((line = buffer.readLine()) != null) {
                sb.append(line);
            }
            in.close();
            buffer.close();
            System.out.println(sb.toString());
            MobileTest stu =  JSONObject.parseObject(sb.toString().substring("__GetZoneResult_ = ".length()), MobileTest.class);
            province = stu.getProvince();
            System.out.println(province);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //从JSONObject对象中读取城市名称
        return province/*jsonObject.getString("cityname")*/;
    }


    private static String test(String mobileNumber){
//        String urlString  =“http://www.0512s.com/c/bd.asp?telno=“;
        String httpUrl = "http://apis.baidu.com/apistore/mobilenumber/mobilenumber?phone="+ mobileNumber;
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
//        httpUrl = httpUrl + “?” + httpArg;
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey",  "您自己的apikey");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
