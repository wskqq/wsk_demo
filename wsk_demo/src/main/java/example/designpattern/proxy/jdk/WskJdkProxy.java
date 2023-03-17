package example.designpattern.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description jdk动态代理代理类
 * @Author Administrator
 * @Date 2022/3/5 20:42
 * @Version 1.0
 */
public class WskJdkProxy implements InvocationHandler {
    private Object target;

    public WskJdkProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理方法执行前");
        Object result = method.invoke(target,args);
        System.out.println("代理方法执行后");

        return result;
    }
}
