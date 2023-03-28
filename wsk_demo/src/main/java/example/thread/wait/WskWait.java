package example.thread.wait;

/**
 *  测试wait在不加锁范围内使用报错问题
 */
public class WskWait {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Thread.currentThread().wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println("结束");
    }
}
