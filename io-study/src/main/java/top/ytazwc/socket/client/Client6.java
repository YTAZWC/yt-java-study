package top.ytazwc.socket.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author yt
 * 2024/6/6
 * 实现一对一聊天；发送者无需收到子集发出去的消息
 * 发送者可以指定接受者的名称，实现一对一私聊
 * 消息格式约定：
 *      客户端发送的消息用冒号分割消息体；“消息类型:消息接收人(用户名):消息内容”
 *      消息类型有两种，login-登录消息、chat-聊天消息
 *      消息接收人可以是all或者具体的用户名，分别表示群聊消息和私聊消息
 */
public class Client6 {

    public static void main(String[] args) {
        try (
            Socket socket = new Socket();
        ) {
            SocketAddress address = new InetSocketAddress("127.0.0.1", 6666);
            // 连接服务器
            socket.connect(address, 2000);
            // 创建一个单独的线程来接收服务器的消息
            new Thread(new ClientHandler6(socket)).start();
            // 向服务器发送的消息的字符输出流
            PrintWriter socketPrintWriter = new PrintWriter(socket.getOutputStream(), true);
            // 接收控制台用户输入的字符输入流
            BufferedReader bufferedInputReader = new BufferedReader(new InputStreamReader(System.in));
            String clientMsg;
            System.out.println("请输入消息:");
            while ((clientMsg = bufferedInputReader.readLine()) != null) {
                // 向服务器发送消息
                socketPrintWriter.println(clientMsg);
                System.out.println("请输入信息:");
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class ClientHandler6 implements Runnable {

    private Socket socket;

    public ClientHandler6(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader socketBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msgFromServer;
            while ((msgFromServer = socketBufferedReader.readLine()) != null) {
                System.out.println("收到来自服务端的消息:" + msgFromServer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
