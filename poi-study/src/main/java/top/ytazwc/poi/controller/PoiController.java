package top.ytazwc.poi.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
//        System.out.println(file);
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
        List<List<Object>> read = reader.read();
        for (List<Object> list : read) {
            for (Object object : list) {
                if (object == null) {
                    continue;
                }
                System.out.print((String) object.toString());
                System.out.print("\t");
            }
            System.out.println();
        }

        return "poi";
    }

}
