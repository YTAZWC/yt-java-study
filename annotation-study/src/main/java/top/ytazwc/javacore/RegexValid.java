package top.ytazwc.javacore;

import java.lang.annotation.*;

/**
 * @author 花木凋零成兰
 * @title RegexValid
 * @date 2024/5/27 11:34
 * @package top.ytazwc.javacore
 * @description 正则校验工具注解
 */
@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER}) // 定义注解作用范围为字段或参数
@Retention(RetentionPolicy.RUNTIME) // 定义注解在运行时有效
public @interface RegexValid {
    enum Policy {
        // @formatter:off
        EMPTY(null),
        DATE("^(?:(?!0000)[0-9]{4}([-/.]?)(?:(?:0?[1-9]|1[0-2])\\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\\1"
                + "(?:29|30)|(?:0?[13578]|1[02])\\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|"
                + "(?:0[48]|[2468][048]|[13579][26])00)([-/.]?)0?2\\2(?:29))$"),
        MAIL("^[A-Za-z0-9](([_\\.\\-]?[a-zA-Z0-9]+)*)@([A-Za-z0-9]+)(([\\.\\-]?[a-zA-Z0-9]+)*)\\.([A-Za-z]{2,})$");
        // @formatter:on

        private String policy;

        Policy(String policy) {
            this.policy = policy;
        }

        public String getPolicy() {
            return policy;
        }
    }
    String value() default "";
    Policy policy() default Policy.EMPTY;
}
