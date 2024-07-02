package top.ytazwc.poi.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.ytazwc.poi.entity.OrderGoods;
import top.ytazwc.poi.entity.OrderGoodsDetail;
import top.ytazwc.poi.util.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author 花木凋零成兰
 * @title PoiController
 * @date 2024/6/30 23:07
 * @package top.ytazwc.poi.controller
 * @description 导入
 */
@RestController
@RequestMapping("/poi")
public class PoiController {

    @PostMapping("/import")
    public String excelImport(MultipartFile file) throws IOException {

        if (file == null) {
            System.out.println("文件不可为空");
            return "文件不可为空";
        }
        // 添加判断
        // 获取源文件名
        String filename = file.getOriginalFilename();
        if (filename == null) {
            return "文件上传出错!";
        }
        // 获取后缀
        String suffix = filename.substring(filename.lastIndexOf("."));
        if (suffix.isEmpty()) {
            return "文件格式错误";
        }
        // 判断是否重复上传


        List<OrderGoods> goodsList = new ArrayList<>();
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());

        List<Sheet> sheets = reader.getSheets();
        System.out.println(sheets.size());
        for (Sheet sheet : sheets) {
            System.out.println(sheet.getSheetName());

        }



        getGoodsList(reader, goodsList);

        reader = ExcelUtil.getReader(file.getInputStream(), 1);
        getGoodsList(reader, goodsList);

        goodsList.forEach(System.out::println);
        return "poi";
    }

    // 读取数据
    private void getGoodsList(ExcelReader reader, List<OrderGoods> goodsList) {
        List<List<Object>> read = reader.read();
        int size = read.size();
        for (int i = 0; i < size; ++ i) {
            List<Object> datas = read.get(i);
            // 判断该列数据类型
            String data = StringUtils.getStrAllTrim(datas.get(0));
            if (data.contains("订货单位")) {
                // 则进入主表数据行
                // 获取 订货单
                OrderGoods orderGoods = getOrderGoods(datas);
                // 下一行则为 详情表 表头行
                orderGoods.setHeaderRowIndex(i+1);
                // 再下一行为 详情表 数据行-起始行
                orderGoods.setStartRowIndex(i+2);
                // 继续遍历获取 详情表 数据行-结束行
                int j;
                for (j = i+2; j < size; ++ j) {
                    List<Object> dataList = read.get(j);
                    // 尝试获取菜品名称
                    String name = StringUtils.getStrAllTrim(dataList.get(1));
                    if (name.isEmpty() && ObjectUtil.isEmpty(orderGoods.getEndRowIndex())) {
                        // 菜品为空，则说明当前为 详情表 数据行-结束行
                        orderGoods.setEndRowIndex(j-1);
//                        System.out.println(j);
                    } else if (name.contains("订货人")) {
                        if (ObjectUtil.isEmpty(orderGoods.getEndRowIndex())) {
                            orderGoods.setEndRowIndex(j-2);
                        }
                        // 到达主表结尾
                        // 订货人在下一列单元格
                        orderGoods.setOrderer(StringUtils.getStrAllTrim(dataList.get(2)));
                        // 复核人 第六
                        orderGoods.setReviewer(StringUtils.getStrAllTrim(dataList.get(6)).substring(4));
                        // 当前主表结束
                        break;
                    }
                }
                // 获取订货单详情列表
                getGoodsDetailList(read, orderGoods);
//                System.out.println(orderGoods);
//                System.out.println();
                goodsList.add(orderGoods);
                // 更新索引所处位置
                i = j+1;
            }
        }
    }

    // 获取订货单
    private OrderGoods getOrderGoods(List<Object> datas) {
        OrderGoods orderGoods = new OrderGoods();
        // 获取订货单位
        String orderUnit = StringUtils.getStrAllTrim(datas.get(0)).substring(5);
        orderGoods.setOrderUnit(orderUnit);
        orderGoods.setType(orderUnit.contains("职工")? "1":"2");
        // 获取供货商
        String supplier = StringUtils.getStrAllTrim(datas.get(4)).substring(4);
        orderGoods.setSupplier(supplier);
        // 获取订货时间
        String timeStr = StringUtils.getStrAllTrim(datas.get(7)).substring(5);
        DateTime date = DateUtil.parse(timeStr);
        // 解析后月份少1
//        System.out.println("month: " + date.month());
        LocalDateTime dateTime = LocalDateTime.of(date.year(), date.month()+1, date.dayOfMonth(),0, 0, 0);
//        String dateStr = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd: HH:mm:ss"));
//        System.out.println(dateStr);
        orderGoods.setTime(dateTime);
        return orderGoods;
    }

    // 根据mapList获取详情列表
    private List<OrderGoodsDetail> getGoodsDetailList(List<Map<String, Object>> read) {
        List<OrderGoodsDetail> result = new ArrayList<>();
        for (Map<String, Object> map : read) {
            OrderGoodsDetail detail = new OrderGoodsDetail();

            // 设置货品名称
            String name = StringUtils.getStrAllTrim(map.get("货品名称"));
            detail.setName(name);
            // 设置品牌
            String brand = StringUtils.getStrAllTrim(map.get("品牌"));
            detail.setBrand(brand);
            // 设置规格
            String spec = StringUtils.getStrAllTrim(map.get("规格"));
            detail.setSpec(spec);
            // 设置产地
            String placeOrigin = StringUtils.getStrAllTrim(map.get("产地"));
            detail.setPlaceOrigin(placeOrigin);
            // 设置生产商
            String producer = StringUtils.getStrAllTrim(map.get("生产商"));
            detail.setProducer(producer);
            // 设置质保期
            String warrantyPeriod = StringUtils.getStrAllTrim(map.get("质保期"));
            detail.setWarrantyPeriod(warrantyPeriod);
            // 设置订货单位
            String unit = StringUtils.getStrAllTrim(map.get("订货单位"));
            detail.setUnit(unit);
            // 设置申购数量
            String purchaseStr = StringUtils.getStrAllTrim(map.get("申购数量"));
            // 空字符串处理
            if (purchaseStr.isEmpty()) {
                purchaseStr = "0";
            }
            String[] numbers = purchaseStr.split("\\+");
            BigDecimal quantity = BigDecimal.valueOf(0d);
            // 求和
            for (String number : numbers) {
                quantity = quantity.add(BigDecimal.valueOf(Double.parseDouble(number)));
            }
            detail.setPurchaseQuantity(quantity.doubleValue());
            // 设置备注
            String remark = StringUtils.getStrAllTrim(map.get("备注"));
            detail.setRemark(remark);

            result.add(detail);
        }
        return result;
    }

    // 获取详情列表
    private void getGoodsDetailList(List<List<Object>> read, OrderGoods orderGoods) {
        List<OrderGoodsDetail> detailList = new ArrayList<>();
        int start = orderGoods.getStartRowIndex();
        int end = orderGoods.getEndRowIndex();
        Set<String> numGoods = new HashSet<>();
        BigDecimal purchaseCount = BigDecimal.valueOf(0d);

        for (int i = start; i <= end; ++ i) {
            List<Object> list = read.get(i);
            OrderGoodsDetail detail = new OrderGoodsDetail();
            // 设置货品名称
            String name = StringUtils.getStrAllTrim(list.get(1));
            detail.setName(name);
            numGoods.add(name);
            // 设置品牌
            String brand = StringUtils.getStrAllTrim(list.get(2));
            detail.setBrand(brand);
            // 设置规格
            String spec = StringUtils.getStrAllTrim(list.get(3));
            detail.setSpec(spec);
            // 设置产地
            String placeOrigin = StringUtils.getStrAllTrim(list.get(4));
            detail.setPlaceOrigin(placeOrigin);
            // 设置生产商
            String producer = StringUtils.getStrAllTrim(list.get(5));
            detail.setProducer(producer);
            // 设置质保期
            String warrantyPeriod = StringUtils.getStrAllTrim(list.get(6));
            detail.setWarrantyPeriod(warrantyPeriod);
            // 设置订货单位
            String unit = StringUtils.getStrAllTrim(list.get(7));
            detail.setUnit(unit);
            // 设置申购数量
            String purchaseStr = StringUtils.getStrAllTrim(list.get(8));
            // 空字符串处理
            if (purchaseStr.isEmpty()) {
                purchaseStr = "0";
            }
            String[] numbers = purchaseStr.split("\\+");
            BigDecimal quantity = BigDecimal.valueOf(0d);
            // 求和
            for (String number : numbers) {
                quantity = quantity.add(BigDecimal.valueOf(Double.parseDouble(number)));
            }
            quantity = quantity.setScale(2, RoundingMode.HALF_UP);
            detail.setPurchaseQuantity(quantity.doubleValue());
            purchaseCount = purchaseCount.add(quantity);
            // 设置备注
            String remark = StringUtils.getStrAllTrim(list.get(9));
            detail.setRemark(remark);
            detailList.add(detail);
        }
        // 设置详情列表
        orderGoods.setDetailList(detailList);
        // 设置订购货品数
        orderGoods.setNumGoods(numGoods.size());
        // 设置申购数量
        purchaseCount = purchaseCount.setScale(2, RoundingMode.HALF_UP);
        orderGoods.setPurchaseQuantity(purchaseCount.doubleValue());
    }

}
