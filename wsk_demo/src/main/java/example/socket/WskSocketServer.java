package example.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 通过传递信息头前的数据长度，来处理数据防止粘包与拆包
 */
public class WskSocketServer {
    public static void main(String[] args) {
        int port = 8888;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            SocketClientHandler thread = new SocketClientHandler(socket);
            thread.start();
        } catch (IOException e) {

        }finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
