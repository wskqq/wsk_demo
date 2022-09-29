package example.customclassloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description 自定义类加载器测试类
 *   测试前提条件：
 *      需要在自定义目录下：D:\test\example\customclassloader
 *      测试的class文件：example.customclassloader.Parent
 *      测试结果：
 *          Step a: example.customclassloader.Parent is loaded by example.customclassloader.CustomClassLoader@7eda2dbb
 *          Step c: Parent instance is created: example.customclassloader.CustomClassLoader@7eda2dbb -> example.customclassloader.Parent@300ffa5d
 *          Step d: Parent instance：example.customclassloader.Parent@300ffa5d, constructor is invoked
 *          My first class loader...
 * @Author wsk
 * @Date 2022/9/29 18:21
 * @Version 1.0
 */
public class CustomTest<declaredMethods> {

    public static void main(String[] args) throws ClassNotFoundException {
        // 创建自定义类加载器
        CustomClassLoader classLoader = new CustomClassLoader("D:\\test"); // E://myclassloader//classpath
        // 动态加载class文件到内存中（无连接）
        Class<?> c = classLoader.loadClass("example.customclassloader.Parent");
        // 通过反射拿到所有的方法
        Method[] declaredMethods = c.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if ("say".equals(method.getName())) {
                // 通过反射拿到children对象
                Object children = null;
                try {
                    children = c.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                // 调用children的say()方法
                try {
                    method.invoke(children);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
