package example.thread.exception;

/**
 * @Description 测试异常的线程
 * @Author wsk
 * @Date 2022/11/11 10:05
 * @Version 1.0
 */
public class WskThreadException implements Runnable {
    @Override
    public void run() {
        int i = Integer.parseInt("测试异常线程");
        System.out.println("测试异常线程");
    }
}
