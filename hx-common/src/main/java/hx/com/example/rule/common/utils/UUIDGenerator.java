package hx.com.example.rule.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Author mingliang
 * @Date 2017-08-31 14:58
 */

public class UUIDGenerator {

    private final static int[] intNumber = new int[]{0,1,2,3,4,5,6,7,8,9};

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String temp = uuid.toString().replaceAll("-","");
        return temp;
    }

    public static String getUUID(String name) {
        UUID uuid =  UUID.fromString(name);
        String temp = uuid.toString().replaceAll("-","");
        return temp;
    }

    public static String getUUID(byte[] name) {
        UUID uuid =  UUID.nameUUIDFromBytes(name);
        String temp = uuid.toString().replaceAll("-","");
        return temp;
    }

    public static String getUUIDNumber(){
        Long current = System.currentTimeMillis();
        CharSequence charSequence = new StringBuffer(System.currentTimeMillis()+getUUID()+"");
        int sequence = charSequence.hashCode();
        String str = getTime()+Long.toString((long) (Math.random() * Long.MAX_VALUE), 64)+current+sequence;
        long length = str.length();
        StringBuffer stringBuffer = new StringBuffer();
        if (length< 64){
            long difference = 64 - length;
            for (int i =0; i< difference;i++){
                String random = Long.toString((long) (Math.random() * Long.MAX_VALUE)).substring(0,1);
                stringBuffer.append(intNumber[Integer.valueOf(random)]);
            }
        }
        return str+stringBuffer.toString();
    }

    private static String getTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpleDateFormat.format(new Date());
    }


}
