package example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description TODO
 * @Author wsk
 * @Date 2023/1/9 17:06
 * @Version 1.0
 */
@SpringBootApplication
public class SpringBootTest {
    public static void main(String[] args) {
//        StackTraceElement stackTraceElement = new RuntimeException().getStackTrace()[0];
//        System.out.println(stackTraceElement.getClassName());
//        System.out.println(stackTraceElement.getClass());
//        System.out.println(stackTraceElement.getFileName());
//        System.out.println(stackTraceElement.getLineNumber());
//        System.out.println(stackTraceElement.getMethodName());

//        SpringApplication.run(SpringBootTest.class, args);

        SpringApplication sa = new SpringApplication(SpringBootTest.class);
        sa.run(args);

    }
}
