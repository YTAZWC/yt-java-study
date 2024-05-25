package top.ytazwc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hoshino
 */
@SpringBootApplication
@MapperScan("top.ytazwc.mapper")
public class SwaggerStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwaggerStudyApplication.class, args);
    }

}
