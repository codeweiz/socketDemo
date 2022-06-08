package cn.microboat;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author zhouwei
 */
@Slf4j
public class SocketClient {

    public static void main(String[] args) {
        SocketClient socketClient = new SocketClient();
        Message message = (Message) socketClient.send(new Message("hello, I'm Socket."), "127.0.0.1", 6666);
        log.info("receive message from socketServer: {}", message.getContent());
    }

    /**
     * 发送消息
     *
     * @param message 消息
     * @param host    ip 地址
     * @param port    端口
     * @return Object
     */
    public Object send(Message message, String host, int port) {

        // 创建 Socket 对象，并绑定 host 和 port
        try (Socket socket = new Socket(host, port)) {

            // 通过输出流，向服务端发送消息
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(message);

            // 通过输入流，接收服务端响应的消息
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            log.error("occur exception:", e);
        }
        return null;
    }
}
