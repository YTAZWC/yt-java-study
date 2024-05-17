package top.ytazwc.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 花木凋零成兰
 * @title DateDTO
 * @date 2024/5/17 22:39
 * @package top.ytazwc.entity.dto
 * @description 接收时间查询参数
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DateDTO {

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 查询种类
     */
    private String type;

}
