package top.ytazwc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ytazwc.annotation.MyAnnotation;
import top.ytazwc.entity.dto.DateDTO;

/**
 * @author 花木凋零成兰
 * @title UserController
 * @date 2024/5/17 22:36
 * @package top.ytazwc.controller
 * @description 用户控制类
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @PostMapping("/date")
    @MyAnnotation
    public String method(@RequestBody DateDTO dto) {
        log.error("dto:{}", dto);
        return "success!";
    }

    @PostMapping("/good")
    public String method2(@RequestBody DateDTO dto) {
        return "success!";
    }

}
