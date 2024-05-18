package top.ytazwc.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.ytazwc.filter.RepeatedlyReadFilter;

/**
 * @author 花木凋零成兰
 * @title FilterConfig
 * @date 2024/5/18 12:51
 * @package top.ytazwc.config
 * @description TODO
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<RepeatedlyReadFilter> myFilter() {
        FilterRegistrationBean<RepeatedlyReadFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RepeatedlyReadFilter());
        // 指定过滤的URL模式
        registrationBean.addUrlPatterns("/user/*");
        return registrationBean;
    }

}
