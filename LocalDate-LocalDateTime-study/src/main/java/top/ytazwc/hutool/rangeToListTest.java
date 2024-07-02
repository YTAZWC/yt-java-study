package top.ytazwc.hutool;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.util.List;

/**
 * @author yt
 * 2024/7/2
 */
public class rangeToListTest {

    public static void main(String[] args) {
        String type = "year";
        String startTime = "2022-01-01";
        String endTime = "2024-12-31";
        switch (type) {
            case "day":
                startTime = DateUtil.formatDateTime(DateUtil.beginOfDay(DateUtil.parseDate(startTime)));
                endTime = DateUtil.formatDateTime(DateUtil.endOfDay(DateUtil.parseDate(endTime)));
                break;
            case "week":
                startTime = DateUtil.formatDateTime(DateUtil.beginOfWeek(DateUtil.parseDate(startTime)));
                endTime = DateUtil.formatDateTime(DateUtil.endOfWeek(DateUtil.parseDate(endTime)));
                break;
            case "month":
                startTime += "-01";
                endTime += "-01";
                startTime = DateUtil.formatDateTime(DateUtil.beginOfMonth(DateUtil.parseDate(startTime)));
                endTime = DateUtil.formatDateTime(DateUtil.endOfMonth(DateUtil.parseDate(endTime)));
                break;
            case "season":
                startTime = DateUtil.formatDateTime(DateUtil.beginOfQuarter(DateUtil.parseDate(startTime)));
                endTime = DateUtil.formatDateTime(DateUtil.endOfQuarter(DateUtil.parseDate(endTime)));
                break;
            case "year":
                startTime = DateUtil.formatDateTime(DateUtil.beginOfYear(DateUtil.parseDate(startTime)));
                endTime = DateUtil.formatDateTime(DateUtil.endOfYear(DateUtil.parseDate(endTime)));
                break;
        }
        System.out.println("startTime: " + startTime);
        System.out.println("endTime: " + endTime);
        List<DateTime> dateTimes = DateUtil.rangeToList(DateUtil.parseDateTime(startTime), DateUtil.parseDateTime(endTime), DateField.YEAR);
        for (DateTime dateTime : dateTimes) {
            System.out.println(DateUtil.year(dateTime));
            System.out.println(DateUtil.formatDate(dateTime));
        }
    }

}
