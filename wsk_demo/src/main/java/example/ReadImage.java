package example;

import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * @Description
 * @Author Administrator
 * @Date 2022/3/15 20:22
 * @Version 1.0
 */
public class ReadImage {
    public static void main(String[] args) {
        String filePath = "D:\\test\\12345.jpg";
        File file = new File(filePath);
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage,"jpg",baos);
            BASE64Encoder base64Encoder = new BASE64Encoder();
            base64Encoder.encode(baos.toByteArray());
            System.out.println("+++++++++++");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
