package top.ytazwc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 花木凋零成兰
 */
@SpringBootApplication
@MapperScan("top.ytazwc.mapper")
public class MpStudyDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MpStudyDemoApplication.class, args);
    }

}
