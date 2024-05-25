package top.ytazwc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.ytazwc.entity.Student;
import top.ytazwc.mapper.StudentMapper;
import top.ytazwc.service.StudentService;

/**
 * @author 花木凋零成兰
 * @title StudentServiceImpl
 * @date 2024/5/25 22:58
 * @package top.ytazwc.service.impl
 * @description TODO
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
}
