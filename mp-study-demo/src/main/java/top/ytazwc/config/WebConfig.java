package top.ytazwc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.ytazwc.interceptor.RepeatedlyReadInterceptor;

import javax.annotation.Resource;

/**
 * @author 花木凋零成兰
 * @title WebConfig
 * @date 2024/5/18 12:44
 * @package top.ytazwc.config
 * @description TODO
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private RepeatedlyReadInterceptor repeatedlyReadInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(repeatedlyReadInterceptor)
                .addPathPatterns("/user/**");
    }
}
