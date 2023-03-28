package example.spring.wsk;

import java.lang.annotation.*;

/**
 * 手写spring扫描注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface WskComponentScan {
    String value();
}
