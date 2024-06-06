package top.ytazwc.socket.io.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

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
public class Server6 {
    // map(用户名,用户地址)
    public static Map<String, Socket> userConnectInfo = new HashMap<>();

    public static void main(String[] args) {
        // 开启一个TCP服务端 占用一个本地端口
        try (ServerSocket serverSocket = new ServerSocket(6666)) {
            System.out.println("server start");
            // 服务端循环不断接受客户端的连接
            while (true) {
                Socket socket;
                try {
                    socket = serverSocket.accept();
                    System.out.println("客户端" + socket.getRemoteSocketAddress() + "上线了");
                    // 为每个客户端分配一个线程
                    Thread workThread = new Thread(new ServerHandler6(socket));
                    workThread.start();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class ServerHandler6 implements Runnable {

    private Socket socket;
    private BufferedReader socketBufferedReader;

    public ServerHandler6(Socket socket) throws IOException {
        this.socket = socket;
        this.socketBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            // 从Socket中获取输入输出流 接收客户端消息
            String msg;
            while ((msg = readMsgFromClient()) != null) {
                // 对接收到的消息 按照消息格式约定切割
                String[] split = msg.split(":");
                // split[0]-消息类型, split[1]-消息接收人, split[2]-消息内容
                // 判断消息是否符合格式要求
                if (("login".equals(split[0]) && split.length != 2)
                        || (!"login".equals(split[0])) && (split.length != 3)) {
                    response("消息格式错误,请用冒号分割,形如:消息类型:消息接收人(用户名):消息内容, 消息类型有两种:login、chat;消息接收人可以是all 或更具体的用户名");
                    continue;
                }
                String msgType = split[0];
                String username = split[1];
                if ("login".equals(msgType)) {
                    if (Server6.userConnectInfo.get(username) == null) {
                        Server6.userConnectInfo.put(username, socket);
                        response("用户【" + username + "】登录成功!");
                    } else {
                        response("用户【" + username + "】已登录,无需重复登录");
                    }
                } else if ("chat".equals(msgType)) {
                    if ("all".equals(username)) {
                        String senderName = getUname();
                        // 群发消息
                        Server6.userConnectInfo.forEach((k, v) -> {
                            if (v != socket) {
                                try {
                                    PrintWriter socketPrintWriter = new PrintWriter(v.getOutputStream(), true);
                                    String sendMsg = "【" + senderName + "】对大家说: " + split[2];
                                    socketPrintWriter.println(sendMsg);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        });
                    } else {
                        if (Server6.userConnectInfo.get(username) == null) {
                            response("用户【" + username + "】不在线");
                        } else {
                            Socket userSocket = Server6.userConnectInfo.get(username);
                            PrintWriter socketPrintWriter = new PrintWriter(userSocket.getOutputStream(), true);
                            String sendMsg = "【" + getUname() + "】对你说: " + split[2];
                            socketPrintWriter.println(sendMsg);
                        }
                    }
                } else {
                    response("消息类型错误,只支持 login/chat");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 当于一个客户端通信结束后 需要关闭对应的socket
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    // 读取客户端发送的消息
    private String readMsgFromClient() {
        try {
            return socketBufferedReader.readLine();
        } catch (IOException e) {
            // 如果捕获到异常 则将该客户端对应的 socket 删除
            System.out.println("客户端" + socket.getRemoteSocketAddress() + "下线了");
            Server6.userConnectInfo.remove(getUname());
            throw new RuntimeException(e);
        }
    }

    // 获取客户端对应的用户名
    private String getUname() {
        String uname = "";
        // 找出socket对应的用户名
        for (Map.Entry<String, Socket> entry : Server6.userConnectInfo.entrySet()) {
            String k = entry.getKey();
            Socket v = entry.getValue();
            if (v == socket) {
                uname = k;
                break;
            }
        }
        return uname;
    }

    // 响应客户端数据
    private void response(String msg) throws IOException {
        PrintWriter socketPrintWriter = new PrintWriter(socket.getOutputStream(), true);
        socketPrintWriter.println(msg);
    }

}
