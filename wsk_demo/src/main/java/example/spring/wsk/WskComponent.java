package example.spring.wsk;

import java.lang.annotation.*;

/**
 * 手写spring注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface WskComponent {
    String value() default "";
}
