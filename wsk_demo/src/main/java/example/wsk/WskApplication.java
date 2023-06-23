package example.wsk;

import example.wsk.aware.WskAware;
import example.wsk.test.WskBaseService;
import example.wsk.test.WskBaseServiceImpl;
import example.wsk.test.WskService;
import example.wsk.test.WskServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @Description spring中子类加了@Service注解后，父类不用再添加注解，通过父类接口及子类接口获取到的都是同一个子类实现类对象,
 *              spring容器中只有一个子类的对象，父类并没有生成对象加入到spring容器中；
 *              如果父类再加注解，不指名注解类名，启动会报错
 * @Author wsk
 * @Date 2023/6/22 22:43
 * @Version 1.0
 */
@SpringBootApplication
public class WskApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(WskApplication.class, args);
        WskServiceImpl wskService = (WskServiceImpl) context.getBean(WskService.class);
        System.out.println(wskService);
        wskService.testWskService();
        wskService.test();

//        WskBaseServiceImpl wskBaseService = (WskBaseServiceImpl) context.getBean("WskBaseService.class");
        WskBaseService wskBaseService = (WskBaseService) context.getBean("WskService");
        System.out.println(wskBaseService);

//        WskBaseServiceImpl wskBaseServiceImpl = (WskBaseServiceImpl) context.getBean("WskBaseService1");
//        System.out.println(wskBaseServiceImpl);
//        wskBaseServiceImpl.test();
//        wskBaseServiceImpl.testPrivate();

        WskAware wskAware = context.getBean(WskAware.class);
        BeanFactory beanFactory = wskAware.getBeanFactory();
        System.out.println(beanFactory);
    }
}
