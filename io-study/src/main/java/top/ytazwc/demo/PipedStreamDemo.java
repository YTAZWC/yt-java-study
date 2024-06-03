package top.ytazwc.demo;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author yt
 * 2024/6/3
 */
public class PipedStreamDemo {
    public static void main(String[] args) {
        Send s = new Send();
        Receive r = new Receive();
        try {
            // 连接管道
            s.getPos().connect(r.getPis());
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        // 启动线程
        new Thread(s).start();
        new Thread(r).start();
    }

    static class Send implements Runnable {
        private PipedOutputStream pos;

        Send() {
            // 实例化输出流
            pos = new PipedOutputStream();
        }

        @Override
        public void run() {
            String str = "Hello World!!!";
            try {
                pos.write(str.getBytes());
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
            try {
                pos.close();
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
        }
        /**
         * 得到该线程的管道输出流
         */
        PipedOutputStream getPos() {
            return pos;
        }
    }
    static class Receive implements Runnable {
        private PipedInputStream pis;

        Receive() {
            pis = new PipedInputStream();
        }

        @Override
        public void run() {
            byte[] b = new byte[1024];
            int len = 0;
            try {
                len = pis.read(b);
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
            try {
                pis.close();
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
            System.out.println("接收的内容为: " + new String(b, 0, len));
        }

        /**
         * 得到此管道的输入流
         */
        PipedInputStream getPis() {
            return pis;
        }

    }

}
