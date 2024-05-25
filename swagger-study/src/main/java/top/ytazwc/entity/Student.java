package top.ytazwc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author 花木凋零成兰
 * @title User
 * @date 2024/5/25 22:32
 * @package top.ytazwc.entity
 * @description 用户类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
@TableName("student")
public class Student implements Serializable {

    @ApiModelProperty(notes = "用户id", example = "1", hidden = true)
    private String id;

    @ApiModelProperty(notes = "用户名", example = "yt")
    private String name;

    @ApiModelProperty(notes = "用户密码")
    private String password;

    @ApiModelProperty(notes = "用户邮箱")
    private String mail;

    @ApiModelProperty(notes = "用户住址")
    private String address;

}
