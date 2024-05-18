package top.ytazwc.interceptor;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import top.ytazwc.annotation.MyAnnotation;
import top.ytazwc.wrapper.RepeatedlyReadRequestWrapper;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.nio.charset.Charset;

/**
 * @author hoshino
 */
@Slf4j
@Component
public class RepeatedlyReadInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 拦截具有指定注解的方法
            // 获取对应方法
            Method method1 = handlerMethod.getMethod();
            // 检查方法是否有指定注解
            MyAnnotation annotation = method1.getAnnotation(MyAnnotation.class);
            if (annotation != null) {
                RepeatedlyReadRequestWrapper requestWrapper;
                if (request instanceof RepeatedlyReadRequestWrapper) {
                    // 签名处理过程 start....
                    requestWrapper = (RepeatedlyReadRequestWrapper) request;
                    log.info("请求Body: {} ", getBodyString(requestWrapper));
                    // 重新设置Body
                    String messgae = getBodyString(requestWrapper);
                    JSONObject jsonObject = JSONObject.parseObject(messgae);
//                    jsonObject.put("startTime", "2026-08-01");
//                    requestWrapper.setBody(jsonObject.toString().getBytes());
                    if ("3".equals(jsonObject.get("type"))) {
                        jsonObject.put("startTime", jsonObject.get("startTime") + "-01");
                        jsonObject.put("endTime", jsonObject.get("endTime") + "-01");
                        requestWrapper.setBody(jsonObject.toString().getBytes());
                        log.info("修改后Body:{} ", getBodyString(requestWrapper));
                    }
                    // 签名处理过程 end....
                }
                // 默认记录后台接口请求日志记录
                String url = request.getRequestURL().toString();
                String method = request.getMethod();
                String uri = request.getRequestURI();
                String queryString = request.getQueryString();
                log.info(String.format("请求参数, url: %s, method: %s, uri: %s, params: %s ", url, method, uri, queryString));
            }

        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        RepeatedlyReadRequestWrapper requestWrapper;
//        if (request instanceof RepeatedlyReadRequestWrapper) {
//            // 测试再次获取Body start....
//            requestWrapper = (RepeatedlyReadRequestWrapper) request;
//            log.info("再次请求Body: {} ", getBodyString(requestWrapper));
//            // 测试再次获取Body end....
//        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 获取请求Body
     *
     * @param request
     *
     * @return
     */
    public static String getBodyString(final ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = cloneInputStream(request.getInputStream());
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    /**
     * Description: 复制输入流
     *
     * @param inputStream
     *
     * @return</br>
     */
    public static InputStream cloneInputStream(ServletInputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buffer)) > -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            byteArrayOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return byteArrayInputStream;
    }
}