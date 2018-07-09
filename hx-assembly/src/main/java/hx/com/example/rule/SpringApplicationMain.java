package hx.com.example.rule;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.StandardEnvironment;
import tk.mybatis.spring.annotation.MapperSqlScan;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author mingliang
 * @Date 2017-08-23 17:50
 */
//@EnableSwagger2Doc
@EnableAspectJAutoProxy
@SpringBootApplication
@ServletComponentScan
@EnableAutoConfiguration
@EnableDubboConfiguration
@tk.mybatis.spring.annotation.MapperScan("hx.com.example.rule.dao") // 通用mapper4 之后MapperScan
@MapperSqlScan(basePackage = "hx.com.example.rule.dao")
@ComponentScan(basePackages={"hx.com.example.rule","hx.http.client","hx.data.mybatis"})//,
@EnableDiscoveryClient
public class SpringApplicationMain {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        SpringApplication.run(SpringApplicationMain.class,args);
        System.out.println("启动成功，耗时 ："+(System.currentTimeMillis() - startTime)+" ms");
    }
}
