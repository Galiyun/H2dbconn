package com.yomean.dbtest.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author hym
 * @SinceDate 2020/09/10 22:40
 * @Description
 */
public class PropertiesUtil {

    /**
     * 获取Properties对象
     * @return
     */
    public static Properties getProperties(){
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties");
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            //todo log
            System.out.println("properties文件未找到!");
        } catch (IOException e) {
            //todo log
            System.out.println("出现IOException");
        } finally {
            try {
                if (null != inputStream){
                    inputStream.close();
                }
            } catch (IOException e) {
                //todo log
                System.out.println("properties文件流关闭出现异常");
            }
        }
        return properties;
    }

    /**
     * 根据key查询value值
     * @param key key
     * @return
     */
    public static String getValue(String key){
        Properties properties = getProperties();
        String value = properties.getProperty(key);
        return value;
    }

    /**
     * 新增/修改数据
     * @param key
     * @param value
     */
    public static void setValue(String key, String value){
        Properties properties = getProperties();
        properties.setProperty(key, value);

        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        path = path + "application.properties";
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(path);
            properties.store(fileOutputStream, "注释");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fileOutputStream){
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                System.out.println("properties文件流关闭出现异常");
            }
        }
    }

    /**
     * 删除和修改只有语句不同
     * properties.remove(key);
     */
}
