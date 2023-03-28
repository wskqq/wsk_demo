package example.spring.wsk;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 手写spring容器
 */
public class WskApplicationContext {

    public WskApplicationContext(Class<?>... benaClass){
        // 1、获取 @WskComponentScan注解配置的值
        List<String> values = new ArrayList<String>(4);
        for(Class c : benaClass){
            WskComponentScan wskComponentScan = (WskComponentScan) c.getDeclaredAnnotation(WskComponentScan.class);
            if(wskComponentScan == null){
                continue;
            }
            String value = wskComponentScan.value();
            values.add(value);
            System.out.println(value);
        }

        // 2、利用类加载器：Bootstrap ->  jre/lib 目录   Ext.. -> jre/ext/lib   App.. -> classpath
        // 此处需要获取的是App类加载器,获取需要扫描的文件，在加载类之后获取扫描路径下所有添加 @Component注解的类
//        ClassLoader classLoader = this.getClass().getClassLoader();
        ClassLoader classLoader = WskApplicationContext.class.getClassLoader();
        for(String value : values){
            // 通过类加载获取特定目录下的资源URL
            URL resource = classLoader.getResource(value.replaceAll("\\.", "\\/"));
            // 根据URL获取文件路径生成文件
            File file = new File(resource.getFile());
            if(file.isDirectory()){
                File[] files = file.listFiles();
                for (File f : files){
                    System.out.println("路径下文件：" + f);
                    String absolutePath = f.getAbsolutePath();
                    String substring = absolutePath.substring(absolutePath.indexOf("example"), absolutePath.indexOf(".class")).replace("\\",".");
                    System.out.println(substring);
                    Class<?> aClass = null;
                    try {
                        aClass = classLoader.loadClass(substring);
                    } catch (ClassNotFoundException e) {
                        System.out.println(e);
                    }
                    if(aClass.isAnnotationPresent(WskComponent.class)){
                        System.out.println("@Component注解的类名：" + aClass.getName());
                    }
                }
            }
        }

        // TODO 解析ComponentScan注解，获取需要扫描的路径，扫描该路径下添加有@Component注解的类，解析类

    }

    public Object getBean(String beanName){
        // TODO 生成bean

        return null;
    }
}
