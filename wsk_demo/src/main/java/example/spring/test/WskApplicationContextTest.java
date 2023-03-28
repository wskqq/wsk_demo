package example.spring.test;

import example.spring.wsk.WskApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试手写spring容器
 */
public class WskApplicationContextTest {
    public static void main(String[] args) {
//        WskApplicationContext wac = new WskApplicationContext(WskAppConfig.class);
        AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(WskAppConfig.class);
    }
}
