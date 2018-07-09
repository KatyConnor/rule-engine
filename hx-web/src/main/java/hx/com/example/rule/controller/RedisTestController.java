package hx.com.example.rule.controller;

import hx.com.example.rule.common.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author mingliang
 * @Date 2018-05-03 17:09
 */
/**
 * @Author mingliang
 * @Date 2018-01-03 15:43
 */
@RequestMapping("/redis")
@RestController
public class RedisTestController {

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/redisTest",method = RequestMethod.GET)
    public String redisTest(){
//        redisUtil.set("rule","test.rule");
        redisUtil.set(123,"test.rule.ds22222");
//        Object ss = redisUtil.get("rule");
        Object sss = redisUtil.get("rule");
        System.out.println(" ,two = "+sss);
        return "成功！";
    }
}
