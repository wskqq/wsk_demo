package example.thread;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
/**
 * @Description TODO
 * @Author acer
 * @Date 2022/3/3 10:39
 * @Version 1.0
 */
public class WskApp
{
    /**
     * 测试多个线程共享同一个对象的ThreadLocal，数据隔离的情况
     */
    static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();
    /**
     * 多个线程共享全局同一个对象的全局变量，数据不隔离
     */
    Map<String,String> map = new HashMap<String,String>();
    public static void main( String[] args )
    {

        WskApp app = new WskApp();
        WskThreadLocalTestOneThread wsk1 = new WskThreadLocalTestOneThread();
        wsk1.setParam(100L);
        wsk1.setWskApp(app);
        wsk1.start();

        WskThreadLocalTestTwoThread wsk2 = new WskThreadLocalTestTwoThread();
        wsk2.setParam(10L);
        wsk2.setWskApp(app);
        wsk2.start();

        //用完必须清除，避免内存泄漏
        threadLocal.remove();
    }
}
