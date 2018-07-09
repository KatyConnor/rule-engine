package hx.com.example.rule.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hc.ala.scheduler.facade.DubboTestFacade;
import hx.com.example.rule.common.utils.ContentType;
import hx.com.example.rule.facade.DubboServiceFacade;
import hx.com.example.rule.facade.RuleManageFacade;
import hx.com.example.rule.form.LoginForm;
import hx.com.example.rule.info.MenuInfo;
import hx.com.example.rule.order.MenuQueryOrder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户登录
 * @Author mingliang
 * @Date 2018-01-31 14:31
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private RuleManageFacade ruleManageFacade;

    @Autowired
    ApplicationContext applicationContext;

//    @Autowired
//    private DubboServiceFacade dubboServiceFacade;

    private static String classPath = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"\\templates\\";

    @RequestMapping(value = "/login",method = RequestMethod.GET,produces = {ContentType.APPLICATION_JSON_UTF_8,
            ContentType.TEXT_XML_UTF_8 })
    public ModelAndView login(ModelAndView modelAndView, ModelMap modelMap, LoginForm form){
        // 1、验证登录信息，然后获取用户权限下的菜单列表
        // 2、初始化查询条件
        MenuQueryOrder order = new MenuQueryOrder();
        order.setSystemCode(form.getSystemCode());
        // 3、将菜单列表数据返回到前端
        List<MenuInfo> menuInfos = ruleManageFacade.getMenuInfos(order);
        modelMap.put("test","测试");
        modelMap.put("menuList",menuInfos);
        modelAndView.setViewName("rule/home");
        return modelAndView;
    }

    /**
     * 点击菜单跳转对应的页面
     * @param
     * @param viewUrl
     * @return
     */
    @RequestMapping(value = "/{viewUrl}",method = RequestMethod.GET,produces = {ContentType.APPLICATION_JSON_UTF_8,
            ContentType.TEXT_XML_UTF_8 })
    public ModelAndView main( @PathVariable("viewUrl") String viewUrl, HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(viewUrl.replaceAll("-","/"));
//        HttpService httpService = applicationContext.getBean("httpService", HttpService.class);
//        System.out.println(httpService);
//        String string = "";
//        try {
//            string = httpService.doGet("https://mp.weixin.qq.com/s/BG8DzGo3KuKVUumLOy1TVQ");
//            string = httpService.doGet("https://www.jfh.com/jfperiodical/public/article/list?filter=tech");
//            string.replaceAll("src=\" ","src=\" https://www.jfh.com/");
//            string.replaceAll("href=\" ","href=\" https://www.jfh.com/");
//            Document doc = Jsoup.connect("http://www.jfh.com/jfperiodical/openhome?m=d03").get();
//            LOGGER.info(" Document = {}",doc);
//            Elements els = doc.getElementsByClass("items items-hover");
//            if (null == els){
//                LOGGER.info("改地址暂时不支持固话查询，请更换其他的查询渠道 url= {}",telUrl);
//            }
//            Element element = (Element) els.get(0).childNodes().get(2);
//            LOGGER.info(" element = {}",element);
//            return element.text();
//            Elements els = doc.getElementsByClass("items items-hover");
//            Element element =  els.get(0);
//            System.out.println(element);
//            string = httpService.doGet("http://www.jfh.com/jfperiodical/openhome?m=d03");
//            FileWriter fw=new FileWriter("E:\\examplecode\\hx-rule\\hx-web\\target\\classes\\templates\\"+modelAndView.getViewName()+".ftl");
//            fw.write(doc.toString());
//            fw.close();
//            System.out.println(string);
//            modelAndView.addObject(string);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return modelAndView;
    }

    @RequestMapping(value = "/myBoke",method = RequestMethod.GET,produces = {ContentType.APPLICATION_JSON_UTF_8,
            ContentType.TEXT_XML_UTF_8 })
    public ModelAndView myBoke(ModelAndView modelAndView){
        modelAndView.setViewName("myindex");
        return modelAndView;
    }

//    @RequestMapping(value = "/dubboTesst",method = RequestMethod.GET,produces = {ContentType.APPLICATION_JSON_UTF_8,
//            ContentType.TEXT_XML_UTF_8 })
//    public ModelAndView dubboTesst(ModelAndView modelAndView){
//        String ss = dubboServiceFacade.message();
//        System.out.println("dddd === "+ss);
//        return modelAndView;
//    }

    public static void main(String[] args) {
        String url = classPath+"rule\\ruleManage.ftl";
        try {
            FileOutputStream outputStream = new FileOutputStream(url);
            System.out.println("fdsfdsf d");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
