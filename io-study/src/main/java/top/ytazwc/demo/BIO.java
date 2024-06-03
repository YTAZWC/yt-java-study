package top.ytazwc.demo;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yt
 * 2024/6/3
 */
public class BIO {
    static boolean stop = false;

    public static void main(String[] args) throws IOException {

        int connectNum = 0;
        int port = 8888;
        ExecutorService service = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(port);
        while (!stop) {
            if (10 == connectNum) {
                stop = true;
            }
            Socket socket = serverSocket.accept();
            service.execute(() -> {
                try {
                    Scanner scanner = new Scanner(socket.getInputStream());
                    PrintStream printStream = new PrintStream(socket.getOutputStream());
                    while (!stop) {
                        // 读取数据并去除前后空格
                        String s = scanner.next().trim();
                        printStream.println("PONG: " + s);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            connectNum ++;
        }
        service.shutdown();
        serverSocket.close();
    }
}
