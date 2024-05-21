package top.ytazwc;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;

import java.util.Date;

/**
 * @author 花木凋零成兰
 * @title TestDate
 * @date 2024/5/21 14:06
 * @package top.ytazwc
 * @description 测试时间引用
 */
public class TestDate {

    public static void main(String[] args) {
        Date startTime = new Date();
        Date endTime = new Date();

        System.out.println(DateUtil.format(startTime, "yyyy-MM-dd"));
        System.out.println(DateUtil.format(endTime, "yyyy-MM-dd"));

        DateDTO dto = new DateDTO();
        Date[] day = getDay(dto);

        System.out.println(DateUtil.format(day[0], "yyyy-MM-dd"));
        System.out.println(DateUtil.format(day[1], "yyyy-MM-dd"));

    }

    public static Date[] getDay(DateDTO dto) {
        // 默认按7天
        Date date = new Date();
        Date startTime = DateUtil.offset(date, DateField.DAY_OF_YEAR, -6);
        Date endTime = date;
        // 判断是否有选定时间
        if (ObjectUtil.isNotEmpty(dto.getStartTime()) && ObjectUtil.isNotEmpty(dto.getEndTime())) {
            startTime = DateUtil.parseDate(dto.getStartTime());
            endTime = DateUtil.parseDate(dto.getEndTime());
        }
        // 初始化区间
        startTime = DateUtil.beginOfDay(startTime);
        endTime = DateUtil.endOfDay(endTime);
        return new Date[]{startTime, endTime};
    }

}

class DateDTO {
    String startTime;
    String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
