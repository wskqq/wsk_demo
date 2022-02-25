package example.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 粘包拆包socket客户端测试类
 */
public class WskSocketClient {
    public static void main(String[] args) throws UnknownHostException {
        int port = 8888;
        Socket socket = null;
        try {
            socket = new Socket(InetAddress.getLocalHost().getHostAddress(),port);
            String message = "wsk ceshi";
            String msgNum = String.format("%06d",message.length());
            String message1 = msgNum + message;
            for(int i=0; i<5; i++){
                if(i == 0){
                    socket.getOutputStream().write(message1.getBytes());
                }else{
                    socket.getOutputStream().write(message.getBytes());
                }
            }
            System.out.println("发送结束");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
