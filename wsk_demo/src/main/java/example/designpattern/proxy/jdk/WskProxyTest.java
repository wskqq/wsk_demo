package example.designpattern.proxy.jdk;

import example.designpattern.proxy.WskProxyInterface;
import example.designpattern.proxy.WskProxyInterfaceImpl;

import java.lang.reflect.Proxy;

/**
 * @Description  jdk动态代理测试类
 * @Author Administrator
 * @Date 2022/3/5 20:52
 * @Version 1.0
 */
public class WskProxyTest {
    public static void main(String[] args) {
        WskJdkProxy wskJdkProxy = new WskJdkProxy(new WskProxyInterfaceImpl());
        WskProxyInterface wskProxyInterface = (WskProxyInterface) Proxy.newProxyInstance(WskProxyTest.class.getClassLoader(),
                new Class[]{WskProxyInterface.class}, wskJdkProxy);
        wskProxyInterface.dosomething("test");
    }
}
