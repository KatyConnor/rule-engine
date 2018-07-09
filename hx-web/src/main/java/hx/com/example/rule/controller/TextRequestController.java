package hx.com.example.rule.controller;

import hx.com.example.rule.common.utils.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author mingliang
 * @Date 2018-04-19 15:31
 */
@Controller
@RequestMapping("/bowen")
public class TextRequestController {

    @Autowired
    ApplicationContext applicationContext;
    //https://m.luckincoffee.com/invited/register?type=zhangzhen&activityNo=NR201801030001&inviteCode=CS13650529099&secondfrom=2&title=%E4%BB%8A%E5%A4%A9%E6%98%9F%E6%9C%9F%E5%9B%9B%EF%BC%8C%20%E9%80%81%E4%BD%A0%E4%B8%80%E6%9D%AF%E5%85%8D%E8%B4%B9%E5%A4%A7%E5%B8%88%E5%92%96%E5%95%A1%EF%BC%8C%E7%94%B2%E6%96%B9%E4%B9%99%E6%96%B9%E9%83%BD%E6%98%AF%E8%BF%9C%E6%96%B9%EF%BC%81&timestamp=1524119253119

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.GET,produces = {ContentType.APPLICATION_JSON_UTF_8,
            ContentType.TEXT_XML_UTF_8 })
    public ModelAndView login(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response){
        HttpService httpService = applicationContext.getBean("httpService", HttpService.class);
        System.out.println(httpService);
        String string = "";
        try {
            string = httpService.doGet("https://blog.csdn.net/u011116672/article/details/52058667");
            View view = new InternalResourceView("https://blog.csdn.net/u011116672/article/details/52058667");
            Map<String, String> model = new HashMap<>();
            model.put("default",string);
            view.render(null,request,response);
            modelAndView.setView(view);
            System.out.println(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

}
