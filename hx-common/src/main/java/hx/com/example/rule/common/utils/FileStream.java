package hx.com.example.rule.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @Author mingliang
 * @Date 2018-06-20 14:34
 */
public class FileStream {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileStream.class);

    /**
     * 复制文件
     * @param source 源文件
     * @param target 目标文件
     * @return
     */
    public static boolean copyFile(File source, File target){
        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;
        try {
            fi = new FileInputStream(source);
            fo = new FileOutputStream(target);
            in = fi.getChannel();//得到对应的文件通道
            out = fo.getChannel();//得到对应的文件通道
            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fi.close();
                in.close();
                fo.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       return false;
    }

    /**
     * 复制文件夹
     * @param source 源文件
     * @param target 目标文件
     * @param next 是否复制内部的文件或者文件夹
     * @return
     */
    public static boolean copyDir(File source, File target,boolean next){
        File parentDir = new File(target,source.getName());
        if (next){
            if (source.isDirectory()){
                if (!parentDir.exists()){
                    parentDir.mkdir();
                }
                File[] files = source.listFiles();
                for (File file : files){
                    boolean copyResult = copyDir(file,parentDir,true);
                    if (!copyResult){
                        return copyResult;
                    }
                }
            }else {
                //如果是文件则进行文件复制
                return copyFile(source,parentDir);
            }
            return true;
        }else {
            try {
                if (source.isDirectory()){
                    parentDir.mkdir();
                }else {
                    parentDir.createNewFile();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    /**
     * 判断该文件或者文件夹是否存在
     * @param url 地址
     * @return true/false
     */
    public static boolean exists(String url){
        File fileDir = new File(url);
        if(!fileDir.exists()){
            return false;
        }
        return true;
    }

    /**
     * 判断文件或者文件夹是否存在，不存在则创建
     * @param url
     */
    public static boolean existsMk(String url){
        File fileDir = new File(url);
        if (!fileDir.exists()){
            try {
                if (fileDir.getName().indexOf(".") == -1){
                    fileDir.mkdir();
                }else {
                    fileDir.createNewFile();
                }
                return true;
            } catch (IOException e) {
                LOGGER.error("文件/文件夹创建失败，{}",e);
            }
        }
        return false;
    }








    public static void main(String[] args) {
        // 文件复制
        long startTime = System.currentTimeMillis();
        boolean result = copyDir(new File("E:\\devcode\\branches"),new File("E:\\code\\"),true);
        if (result){
            System.out.println("复制完成！");
        }
        System.out.println("耗时："+(System.currentTimeMillis() - startTime)/1000+" 秒");
        // 文件夹复制
    }
}
