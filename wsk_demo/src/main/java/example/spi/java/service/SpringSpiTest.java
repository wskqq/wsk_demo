package example.spi.java.service;

import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.List;

/**
 * @Description TODO
 * @Author wsk
 * @Date 2023/3/17 18:00
 * @Version 1.0
 */
public class SpringSpiTest {
    public static void main(String[] args) {
        List<JavaSpiService> list = SpringFactoriesLoader.loadFactories(JavaSpiService.class, SpringSpiTest.class.getClassLoader());
        for (JavaSpiService javaSpiService : list) {
            System.out.println("java spi 加载的类信息[" + javaSpiService + "]");
        }

    }
}
