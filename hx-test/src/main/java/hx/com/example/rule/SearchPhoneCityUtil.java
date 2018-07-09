package hx.com.example.rule;

import com.alibaba.fastjson.JSONObject;
import com.google.i18n.phonenumbers.PhoneNumberToCarrierMapper;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author mingliang
 * @Date 2018-04-11 15:52
 */
public class SearchPhoneCityUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchPhoneCityUtil.class);

    private static PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    private static PhoneNumberToCarrierMapper carrierMapper = PhoneNumberToCarrierMapper.getInstance();

    private static PhoneNumberOfflineGeocoder geocoder = PhoneNumberOfflineGeocoder.getInstance();

    private static String telUrl = "http://www.00cha.com/114.asp?t=%s";
    private static String taobaoUrl = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=%s";
    private static List<String> countyCodeList = new ArrayList<>();

    static {
        countyCodeList.add("86");
        countyCodeList.add("852");
        countyCodeList.add("853");
        countyCodeList.add("886");
    }

    /**
     * 查询手机号归属地
     * @param phoneNo
     * @return
     */
    public static String doQueryMobileCity(String phoneNo){
        String city = null;
        Pattern pattern = Pattern.compile("^[0-9]*$");

        StringBuilder sb = new StringBuilder();
        for (int i = 0;i < phoneNo.length(); i++){
            Matcher matcher = pattern.matcher(String.valueOf(phoneNo.charAt(i)));
            if (matcher.matches()){
                sb.append(phoneNo.charAt(i));
            }
        }

        for (int i =0; i< countyCodeList.size(); i++){
            city = getGeo(sb.toString(),countyCodeList.get(i));
            if (StringUtils.isNotBlank(city)){
                return city;
            }
        }

//        LOGGER.info("查询电话 = {} ",sb.toString());
//        try {
//            MobileCityResult mobileCityResult = calcMobileCity(sb.toString(),taobaoUrl);
//            LOGGER.info("淘宝查询结果 Province = {} ",mobileCityResult.getProvince());
//            if (StringUtils.isBlank(mobileCityResult.getProvince())){
//                city = getFixedPhoneCity(sb.toString());
//                LOGGER.info("座机查询结果 = {} ",city);
//            }else {
//                city = mobileCityResult.getProvince();
//            }
//        } catch (MalformedURLException e) {
//            LOGGER.error("查询电话号码归属地错误! ",e);
//        } catch (IOException e) {
//            LOGGER.error("查询电话号码归属地错误! ",e);
//        }
//        LOGGER.info("号码归属地为 = {} ",city);
        return "未查询到归属地";
    }

    private static String alibaba(String phoneNo){
        String city = null;

        LOGGER.info("查询电话 = {} ",phoneNo);
        try {
            MobileCityResult mobileCityResult = calcMobileCity(phoneNo,taobaoUrl);
            LOGGER.info("淘宝查询结果 Province = {} ",mobileCityResult.getProvince());
            if (StringUtils.isBlank(mobileCityResult.getProvince())){
                city = getFixedPhoneCity(phoneNo);
                LOGGER.info("座机查询结果 = {} ",city);
            }else {
                city = mobileCityResult.getProvince();
            }
            return city;
        } catch (MalformedURLException e) {
            LOGGER.error("查询电话号码归属地错误! ",e);
        } catch (IOException e) {
            LOGGER.error("查询电话号码归属地错误! ",e);
        }
        LOGGER.info("号码归属地为 = {} ",city);
        return "未查询到归属地";
    }

    /**
     * 手机号查询归属地
     * @param mobileNumber
     * @param requestUrl
     * @return
     * @throws MalformedURLException
     */
    private static MobileCityResult calcMobileCity(String mobileNumber,String requestUrl) throws MalformedURLException {

        StringBuffer sb = new StringBuffer();
        BufferedReader buffer;
        URL url = new URL(String.format(requestUrl,mobileNumber));
        MobileCityResult mobileCityResult = null;
        LOGGER.info("taobaourl = {} ",String.format(requestUrl,mobileNumber));
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
            mobileCityResult =  JSONObject.parseObject(sb.toString().substring("__GetZoneResult_ = ".length()), MobileCityResult.class);
        } catch (Exception e) {
            LOGGER.error("查询电话号码归属地错误! ",e);
        }
        return mobileCityResult;
    }

    /**
     * 固定电话号获取归属地
     * @param phoneNo
     * @return
     */
    private static String getFixedPhoneCity(String phoneNo) throws IOException {
        LOGGER.info("otherUrl = {} ",String.format(telUrl,phoneNo));
        Document doc = Jsoup.connect(String.format(telUrl,phoneNo)).get();
        LOGGER.info(" Document = {}",doc);
        Elements els = doc.getElementsByClass("searchnr");
        if (null == els){
            LOGGER.info("改地址暂时不支持固话查询，请更换其他的查询渠道 url= {}",telUrl);
        }
        Element element = (Element) els.get(0).childNodes().get(2);
        LOGGER.info(" element = {}",element);
        return element.text();
    }

    /**
     * 根据国家代码和手机号  判断手机号是否有效
     * @param phoneNumber
     * @param countryCode
     * @return
     */
    public static boolean checkPhoneNumber(String phoneNumber, String countryCode){

        int ccode = Integer.valueOf(countryCode);
        long phone = Long.valueOf(phoneNumber);

        Phonenumber.PhoneNumber pn = new Phonenumber.PhoneNumber();
        pn.setCountryCode(ccode);
        pn.setNationalNumber(phone);

        return phoneNumberUtil.isValidNumber(pn);

    }

    /**
     * 根据国家代码和手机号  判断手机运营商
     * @param phoneNumber
     * @param countryCode
     * @return
     */
    public static String getCarrier(String phoneNumber, String countryCode){

        int ccode = Integer.valueOf(countryCode);
        long phone = Long.valueOf(phoneNumber);

        Phonenumber.PhoneNumber pn = new Phonenumber.PhoneNumber();
        pn.setCountryCode(ccode);
        pn.setNationalNumber(phone);
        //返回结果只有英文，自己转成成中文
        String carrierEn = carrierMapper.getNameForNumber(pn, Locale.ENGLISH);
        String carrierZh = "";
        carrierZh += geocoder.getDescriptionForNumber(pn, Locale.CHINESE);
        switch (carrierEn) {
            case "China Mobile":
                carrierZh += "移动";
                break;
            case "China Unicom":
                carrierZh += "联通";
                break;
            case "China Telecom":
                carrierZh += "电信";
                break;
            default:
                break;
        }
        return carrierZh;
    }


    /**
     *
     * @Description: 根据国家代码和手机号  手机归属地
     * @param @param phoneNumber
     * @param @param countryCode
     * @param @return    参数
     * @throws
     */
    public static String getGeo(String phoneNumber, String countryCode){

        int ccode = Integer.valueOf(countryCode);
        long phone = Long.valueOf(phoneNumber);

        Phonenumber.PhoneNumber pn = new Phonenumber.PhoneNumber();
        pn.setCountryCode(ccode);
        pn.setNationalNumber(phone);
        return geocoder.getDescriptionForNumber(pn, Locale.CHINESE);
    }

    public static void main(String[] args) {
       String res =  doQueryMobileCity("4008702315");
        String all = alibaba("4008702315");
        System.out.println(res);
        System.out.println(all);
    }
}
