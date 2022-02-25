package example.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class SocketClientHandler extends Thread{
    private Socket socket;

    public SocketClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream= socket.getInputStream();
            int bufferNum = 6;
            byte[] byteParam = new byte[bufferNum];
            int bufferNums = 0;
            if(inputStream.read(byteParam) > 0){
                System.out.println(new String(byteParam));
                bufferNums = Integer.valueOf(new String(byteParam));
            }
            byte[] byteParams = new byte[bufferNums];
            while(inputStream.read(byteParams) > 0){
                System.out.println("结果：" + new String(byteParams));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
