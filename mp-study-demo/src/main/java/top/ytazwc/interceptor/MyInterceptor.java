package top.ytazwc.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.ytazwc.annotation.MyAnnotation;
import top.ytazwc.wrapper.JsonParameterRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;


/**
 * @author 花木凋零成兰
 * @title MyInterceptor
 * @date 2024/5/17 23:00
 * @package top.ytazwc.interceptor
 * @description TODO
 */
@Component
@Slf4j
public class MyInterceptor implements HandlerInterceptor {

    private static final ThreadLocal<String> JSON_STRING = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取对应方法
            Method method = handlerMethod.getMethod();
            // 检查方法是否有指定注解
            MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
            if (annotation != null) {
                log.warn("拦截方法名: {}", method.getName());
                // 执行拦截逻辑
                return checkPermission(annotation, request, response);
            }
        }

        // 如果没有注解或检查通过 则放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        JSON_STRING.remove();
    }

    private boolean checkPermission(MyAnnotation annotation, HttpServletRequest request, HttpServletResponse response) throws IOException {

//        try (
//                InputStream inputStream = request.getInputStream();
//                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//                ) {
//            String json = reader.lines().collect(Collectors.joining());
//            log.warn("json: {}", json);
//            JSON_STRING.set(json);
//        } catch (IOException e) {
//            System.out.println("异常?????");
//            log.error(e.getMessage());
//        }

        JsonParameterRequestWrapper requestWrapper = new JsonParameterRequestWrapper(request);
        String bodyMessage = requestWrapper.getBodyMessage();
        // 输出JSON
        log.warn("JSON: {}",bodyMessage);

        requestWrapper.setBody(bodyMessage.getBytes());

        // request = requestWrapper;
        return true;
    }


}
