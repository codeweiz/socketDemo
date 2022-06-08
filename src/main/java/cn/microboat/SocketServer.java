package cn.microboat;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zhouwei
 */
@Slf4j
public class SocketServer {

    public static void main(String[] args) {
        SocketServer socketServer = new SocketServer();
        socketServer.start(6666);
    }

    /**
     * 开启 SocketServer
     *
     * @param port 端口
     */
    public void start(int port) {
        // 创建 ServerSocket 对象，绑定 port 端口
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            Socket socket;

            // 当 serverSocket.accept() != null 时
            // 通过 accept 方法监听客户端请求
            while ((socket = serverSocket.accept()) != null) {
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

                // 通过输入流，从客户端接收消息
                Message message = (Message) objectInputStream.readObject();
                log.info("serverSocket receive message: {}", message.getContent());

                // 通过输出流，向客户端传送消息
                message.setContent("I've received your message!, I'm socketServer!");
                objectOutputStream.writeObject(message);
                objectOutputStream.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            log.error("occur exception: ", e);
        }
    }
}
