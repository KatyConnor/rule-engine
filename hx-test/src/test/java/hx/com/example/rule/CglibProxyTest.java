package hx.com.example.rule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hx.com.example.rule.common.proxy.ObjectCglibProxyBean;
import hx.com.example.rule.dao.RuleDrlMapperDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author mingliang
 * @Date 2018-01-10 10:34
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = SpringApplicationMain.class)
@TestPropertySource(locations={"classpath:application.properties","classpath:application-local.properties"})
@ComponentScan("hx.com.example.rule")
public class CglibProxyTest {

    @Autowired
    private ObjectCglibProxyBean objectCglibProxy;

    @Autowired
    private RuleDrlMapperDAO ruleDrlMapperDAO;

    @Test
    public void testProxy(){
//        RuleDomain domain = objectCglibProxy.createObject(RuleDomain.class,null,null);

//        TestObject object =  objectCglibProxy.createObject(TestObject.class,null,null);
//        ruleDrlMapperDAO.selectAll();
//        object.testCreate();
//        System.out.println("======================="+object);
    }

    public static void main(String[] args) {
//            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(new Date());
//            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 3);
//            System.out.println(sf.format(calendar.getTime()));

//            String ss = "1313232sdsdf";
//            String ssss = "aaasdsdff55555";
//            StringBuilder sb = new StringBuilder();
////            String ff = "{\"account\":\"1313232sdsdf\",\"loanNumber\":\"aaasdsdff55555\",\"num\":1}";
//            sb.append("{\"account\":\"").append(ss).append("\",").append("\"loanNumber\":\"").append(ssss).append("\"}");
//            System.out.println(sb.toString());
//            System.out.println(JSONObject.parseObject(sb.toString(),TSSest.class));
//            System.out.println(JSONObject.toJSONString(JSONObject.parseObject(sb.toString(),TSSest.class)));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            if (!list.contains("ceshi")){
                list.add("ceshi");
            }
        }
        for (String str : list){
            System.out.println(str);
        }
    }

    public class TSSest implements Serializable{
        private String account;
        private String loanNumber;

        public TSSest(String account, String loanNumber) {
            this.account = account;
            this.loanNumber = loanNumber;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getLoanNumber() {
            return loanNumber;
        }

        public void setLoanNumber(String loanNumber) {
            this.loanNumber = loanNumber;
        }
    }
}
