package hx.com.example.rule.common.utils;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * @Author mingliang
 * @Date 2017-08-30 17:57
 */

public class ObjectUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(ObjectUtils.class);

    private final static String LEFT_CURLY_BRACKET = "{";
    private final static String RIGHT_CURLY_BRACES = "}";
    private final static String COMMA = ",";
    private final static String SPACE = "";


    public static String objectToString(Object obj){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(LEFT_CURLY_BRACKET);
        toString(obj,obj.getClass(),stringBuffer);
        if (!LEFT_CURLY_BRACKET.equals(stringBuffer.toString())){
            stringBuffer.replace(stringBuffer.lastIndexOf(COMMA),stringBuffer.lastIndexOf(COMMA)+1,SPACE).append(RIGHT_CURLY_BRACES);
        }else {
            stringBuffer.append(RIGHT_CURLY_BRACES);
        }
        return stringBuffer.toString();
    }

    private static void toString(Object obj,Class<?> classzz,StringBuffer sb ){
        try {
            Field[] fields =classzz.getDeclaredFields();
            for (int i =0; i< fields.length; i++){
                Field field = fields[i];
                field.setAccessible(true);
                sb.append(field.getName()).append(":").append("java.util.Date".equals(field.getType().getName()) == true
                       && field.get(obj) !=null ? DateUtils.dateFormat((Date) field.get(obj)):field.get(obj)).append(",");
            }
            Class<?> object = classzz.getSuperclass();
            if (object != null && !"java.lang.Object".equals(object.getName())){
                toString(obj,object,sb);
            }
        } catch (ReflectiveOperationException e) {
            LOGGER.info("Failed to get object property and attribute value ！",e);
        }
    }

    public static String toString(Object object){
        SerializeWriter out = new SerializeWriter();
        String var15;
        try {
            JSONSerializer serializer = new JSONSerializer(out);
            serializer.setDateFormat("yyyy-MM-dd hh:MM:ss");
            serializer.config(SerializerFeature.WriteDateUseDateFormat, true);

            serializer.write(object);
            var15 = out.toString();
        } finally {
            out.close();
        }

        return var15;
    }
}
