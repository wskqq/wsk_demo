package example.spi.java.service;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @Description 测试java的SPI功能，需要一个接口及其实现类，还要创建该目录META-INF.services并
 *              该目录下创建以接口全限定名的文件，文件内容中存放该接口的实现类全限定名
 * @Author wsk
 * @Date 2023/3/17 16:16
 * @Version 1.0
 */
public class JavaSpiTest {
    public static void main(String[] args) {
        ServiceLoader<JavaSpiService> sl = ServiceLoader.load(JavaSpiService.class);
        Iterator<JavaSpiService> iterator = sl.iterator();
        while (iterator.hasNext()){
            System.out.println("java spi 加载的类信息[" + iterator.next() + "]");
        }
    }
}
