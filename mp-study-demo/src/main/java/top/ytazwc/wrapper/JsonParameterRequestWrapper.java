package top.ytazwc.wrapper;

import top.ytazwc.wrapper.utils.StreamUtil;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author 花木凋零成兰
 * @title JsonParameterRequestWrapper
 * @date 2024/5/18 9:14
 * @package top.ytazwc.wrapper
 * @description JSON格式的报文请求
 */
public class JsonParameterRequestWrapper extends HttpServletRequestWrapper {
    //用于保存读取body中数据
    private  byte[] body;
    private String bodyMessage;
    public JsonParameterRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        //读取请求的数据保存到本类当中
        //body = StreamUtil.readBytes(request.getInputStream(), "UTF-8");
        body = StreamUtil.readStream(request.getInputStream(), request.getContentLength());
        bodyMessage =  new String(body, StandardCharsets.UTF_8);
    }

    //覆盖（重写）父类的方法
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
    //覆盖（重写）父类的方法
    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    }

    public String getBodyMessage() {
        return bodyMessage;
    }
}
