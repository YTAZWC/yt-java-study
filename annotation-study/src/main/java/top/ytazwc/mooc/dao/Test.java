package top.ytazwc.mooc.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 花木凋零成兰
 * @title Test
 * @date 2024/5/27 16:19
 * @package top.ytazwc.mooc.dao
 * @description TODO
 */
public class Test {

    public static void main(String[] args) {

        Filter f1 = new Filter();
        // 查询id为10的用户
        f1.setId(10);

        Filter f2 = new Filter();
        // 模糊用户名为luck
        f2.setUserName("luck");
        f2.setAge(18);

        Filter f3 = new Filter();
        // 查询邮箱为其中任意一个打的用户
        f3.setMail("liu@sina.com,zh@163.com,77777@qq.com");

        String sql1 = query(f1);
        String sql2 = query(f2);
        String sql3 = query(f3);

        System.out.println(sql1);
        System.out.println(sql2);
        System.out.println(sql3);

    }

    public static String query(Object f) {
        StringBuilder sb = new StringBuilder();
        // 1. 获取到class
        Class<?> c = f.getClass();
        // 2. 获取到table的名字
        boolean exists = c.isAnnotationPresent(Table.class);
        if (!exists) {
            return null;
        }
        Table t = c.getAnnotation(Table.class);
        String tableName = t.value();
        sb.append("select * form ").append(tableName).append(" where 1 = 1");
        // 3. 遍历所有的字段
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            // 4. 处理每个字段对应的sql
            // 4.1 拿到字段名
            boolean fExists = field.isAnnotationPresent(Column.class);
            if (!fExists) {
                continue;
            }
            Column column = field.getAnnotation(Column.class);
            String columnName = column.value();
            // 4.2 拿到字段值
            String filedName = field.getName();
            String getMethodName = "get" + filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
            Object fieldValue;
            try {
                Method method = c.getMethod(getMethodName);
                fieldValue = method.invoke(f);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            // 4.3 拼接SQL
            if (fieldValue == null|| ((fieldValue instanceof Integer) && (Integer)fieldValue == 0)) {
                continue;
            }
            sb.append(" and ").append(columnName).append(" = ");
            if (fieldValue instanceof String) {
                if (((String) fieldValue).contains(",")) {
                    String[] values = ((String)fieldValue).split(",");
                    sb.append("in(");
                    for (String value : values) {
                        sb.append("'").append(value).append("'").append(",");
                    }
                    sb.deleteCharAt(sb.length() - 1);
                    sb.append(")");
                } else {
                    sb.append("'").append(fieldValue).append("'");
                }
            } else if (fieldValue instanceof Integer) {
                sb.append(fieldValue);
            }
        }
        return sb.toString();
    }

}
