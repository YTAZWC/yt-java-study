package top.ytazwc.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ytazwc.entity.Student;
import top.ytazwc.service.StudentService;

/**
 * @author 花木凋零成兰
 * @title StudentController
 * @date 2024/5/25 23:06
 * @package top.ytazwc.controller
 * @description TODO
 */
@Api(value = "学生管理")
@RestController
@Slf4j
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @PostMapping("/add")
    @ApiOperation(value = "新增学生")
    public boolean add(@RequestBody Student student) {
        return service.saveOrUpdate(student);
    }

}
