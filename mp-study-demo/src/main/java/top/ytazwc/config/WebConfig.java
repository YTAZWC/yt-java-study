package top.ytazwc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.ytazwc.interceptor.MyInterceptor;

import javax.annotation.Resource;

/**
 * @author 花木凋零成兰
 * @title WebConfig
 * @date 2024/5/17 23:22
 * @package top.ytazwc.config
 * @description TODO
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private MyInterceptor myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor)
                .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.jpg", "/**/*.jpeg", "/**/*.png");
    }
}
