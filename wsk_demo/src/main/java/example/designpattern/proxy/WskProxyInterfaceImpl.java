package example.designpattern.proxy;

/**
 * @Description jdk动态代理被代理类
 * @Author Administrator
 * @Date 2022/3/5 20:51
 * @Version 1.0
 */
public class WskProxyInterfaceImpl implements WskProxyInterface {
    @Override
    public void dosomething(String param) {
        System.out.println(param);
    }
}
