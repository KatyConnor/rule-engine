package hx.com.example.rule.common.velocity;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author mingliang
 * @Date 2018-01-30 17:04
 */
public class VelocityFactory{

    private static VelocityContext context = new VelocityContext();
    private static VelocityEngine velocityEngine = new VelocityEngine();

    static {
        initVelocity();
    }

    private static void initVelocity(){
        // 初始化velocity
        velocityEngine.init();
        // 添加函数实体
        context.put("v", VelocityUtils.class);

    }


    /**
     * 模板渲染解析，返回解析之后的值
     * @param temp
     * @param values
     * @return
     * @throws Exception
     */
    public static String evaluate(String temp, Map<String,Object> values){
        convertVelocityContext(values);
        StringWriter sw = new StringWriter();
        velocityEngine.evaluate( context, sw,"",temp );
        return sw.toString();
    }


    /**
     * <pre>
     *   把Map转换成Context，将所有的值存放在 context中
     * </pre>
     * @param map 参数值
     */
    private static void convertVelocityContext(Map<String, Object> map) {
        if (map == null) {
            return;
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            context.put(entry.getKey(), entry.getValue());
        }
    }

    public static void main(String[] args) {
        String temp = "this is a velocity test , $!{name} is ok ";
        Map<String,Object> valueMap = new HashMap<>();
        valueMap.put("name","testName");
        String result = evaluate(temp,valueMap);
        System.out.println(result);
        System.out.println(temp.indexOf(" is "));
        System.out.println(temp.substring(temp.indexOf(" is ")+1,temp.indexOf(" is ")+3).trim());
    }

}
