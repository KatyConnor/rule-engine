package hx.com.example.rule.common.utils;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author mingliang
 * @Date 2018-06-20 11:31
 */
public class ExcelTest {
    public static void main(String[] args) {
        String os = System.getProperty("os.name");
        System.out.println(os);
        try {
            if (os.toLowerCase().startsWith("win")) {
                String arstringCommand = "cmd /c copy /y E:\\王明亮-java高级工程师-15826166815.doc F:\\王明亮-java高级工程师-15826166815-bak.doc";
                Process process = null;
                process = Runtime.getRuntime().exec(arstringCommand);
                process.waitFor();
            } else {
                String command = "cp test.txt test-bak.txt";
                Process process = null;
                process = Runtime.getRuntime().exec(command);
                process.waitFor();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        File parentDir = new File("\\\\10.244.76.8\\数据备份\\AI录音\\录音报表\\AI报表\\"+(calendar.get(Calendar.MONTH )+1)+"月\\");
        // 如果文件存在先备份一个文件
        FileStream.copyFile(new File(parentDir+"\\"+"8740.xls"),new File(parentDir,"8740-"+sf.format(new Date())+"-bak.xls"));
    }
}
