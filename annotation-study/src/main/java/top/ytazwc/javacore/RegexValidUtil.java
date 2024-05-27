package top.ytazwc.javacore;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 花木凋零成兰
 * @title RegexValidUtil
 * @date 2024/5/27 13:54
 * @package top.ytazwc.javacore
 * @description @RegexValid注解处理器
 */
public class RegexValidUtil {

    public static boolean check(Object obj) throws Exception {
        boolean result = true;
        StringBuilder sb = new StringBuilder();
        Class<?> aClass = obj.getClass();
        // 获取该类中定义的所有字段
        Field[] fields = aClass.getDeclaredFields();
        // 遍历判断
        for (Field field : fields) {
            // 判断成员是否被 @RegexValid 注解所修饰
            if (field.isAnnotationPresent(RegexValid.class)) {
                // 获取对应注解
                RegexValid valid = field.getAnnotation(RegexValid.class);
                // 获取注解值
                String value = valid.value();
                if ("".equals(value)) {
                    // 如果 value 为空字符串 则说明没有注入自定义正则表达式 该用 policy 属性
                    RegexValid.Policy policy = valid.policy();
                    value = policy.getPolicy();
                }
                // 先确定获取成员的访问权限 避免无法访问私有成员
                field.setAccessible(true);
                Object fieldObj = null;
                try {
                    fieldObj = field.get(obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace(System.out);
                }
                if (fieldObj == null) {
                    sb.append("\n")
                      .append(String.format("%s 类中的 %s 字段不能为空! ", aClass.getName(), field.getName()));
                    result = false;
                } else {
                    if (fieldObj instanceof String) {
                        String text = (String) fieldObj;
                        Pattern pattern = Pattern.compile(value);
                        Matcher matcher = pattern.matcher(text);
                        result = matcher.matches();
                        if (!result) {
                            sb.append("\n")
                              .append(String.format("%s 不是合法的 %s ! ", text, field.getName()));
                        }
                    } else {
                        sb.append("\n")
                          .append(String.format("%s 类中的 %s 字段不是String类型,不能使用此注解校验!", aClass.getName(), field.getName()));
                        result = false;
                    }
                }
            }
        }
        if (sb.length() > 0) {
            throw new Exception(sb.toString());
        }
        return result;
    }

}
