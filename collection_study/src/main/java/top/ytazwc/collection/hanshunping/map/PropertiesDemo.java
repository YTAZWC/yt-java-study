package top.ytazwc.collection.hanshunping.map;

import java.util.Properties;

/**
 * @author 花木凋零成兰
 * @title PropertiesDemo
 * @date 2024-11-18 22:43
 * @package top.ytazwc.collection.hanshunping.map
 * @description
 */
public class PropertiesDemo {

    public static void main(String[] args) {

        /*
        1、Properties 继承 Hashtable
        2、通过k-v存放数据，k和v不能为null
         */

        Properties properties = new Properties();
        properties.put("john", 100);
        properties.put("lucy", 100);
        properties.put("lic", 100);
        properties.put("lic", 88);

        properties.forEach((k, v) -> System.out.println(k + " = " + v));

    }

}
