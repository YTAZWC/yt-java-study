package top.ytazwc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 花木凋零成兰
 * @title User
 * @date 2024/5/8 11:58
 * @package top.ytazwc.entity
 * @description 用户类
 */
@Data
@TableName("user")
public class User {
    private int id;
    private String name;
    private int age;
    private String email;
}
