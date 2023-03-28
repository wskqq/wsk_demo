package example.spring.test;

import example.spring.wsk.WskComponent;
import example.spring.wsk.WskComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * spring配置类
 */
//@WskComponentScan("example.spring.service")
@ComponentScan(value = "example.spring.service")
public class WskAppConfig {

}
