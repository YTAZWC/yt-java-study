package top.ytazwc.mooc.dao;

import lombok.*;

/**
 * @author 花木凋零成兰
 * @title Filter
 * @date 2024/5/27 16:08
 * @package top.ytazwc.mooc.dao
 * @description TODO
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("user")
public class Filter {
    @Column("id")
    private Integer id;
    @Column("user_name")
    private String userName;
    @Column("nick_user")
    private String nickName;
    @Column("age")
    private Integer age;
    @Column("city")
    private String city;
    @Column("mail")
    private String mail;
    @Column("phone")
    private String phone;
}
