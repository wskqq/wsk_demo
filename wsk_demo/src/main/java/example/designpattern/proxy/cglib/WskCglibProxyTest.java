package example.designpattern.proxy.cglib;

import example.designpattern.proxy.WskProxyInterfaceImpl;
import net.sf.cglib.proxy.Enhancer;

/**
 * cglib动态代理
 */
public class WskCglibProxyTest {
    public static void main(String[] args) {
        // 字节码操作工具
        Enhancer enhancer = new Enhancer();
        // 传入父类的结构信息
        enhancer.setSuperclass(WskProxyInterfaceImpl.class);
        // 创建一个扩展对象
        WskCglibProxy wskCglibProxy = new WskCglibProxy();
        // 将扩展对象添加到工具中
        enhancer.setCallback(wskCglibProxy);
        // 创建代理类
        WskProxyInterfaceImpl wskProxyInterface = (WskProxyInterfaceImpl) enhancer.create();
        wskProxyInterface.dosomething("cglib代理");

    }
}
