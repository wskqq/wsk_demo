package example.thread.exception;

import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.*;

/**
 * @Description 线程异常测试类
 * @Author wsk
 * @Date 2022/11/11 10:07
 * @Version 1.0
 */
public class WskThreadExceptionTest {
    public static void main(String[] args) {
        // 1.测试无捕获异常时的场景
//        testException();

        // 2.测试有捕获异常时的场景
//        testCatchException();

        // 3.线程池异常捕获的场景:
        // 线程中使用try...catch捕获异常、
        // 重写ThreadPoolExecutor的afterExecute方法、
//        overrideAfterExecute();
        // 调用submit方法返回Future对象中可以获取异常
        submitCatchException();

        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            Thread.currentThread().isInterrupted();
        }
        System.out.println("主线程结束");
    }

    /**
     * 调用submit方法返回Future对象中可以获取异常,异常线程将不会再打印异常堆栈信息,
     * f.get()为阻塞方法，只有线程执行完成之后该方法才能执行
     */
    private static void submitCatchException() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 4, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10), new CustomizableThreadFactory("Wsk-pool-"),
                new ThreadPoolExecutor.CallerRunsPolicy());
        Future<?> f = threadPoolExecutor.submit(new WskThreadException());
        try {
            System.out.println(f.get());
        } catch (InterruptedException e) {
            Thread.currentThread().isInterrupted();
        } catch (ExecutionException e) {
            System.out.println("异常" + e);
        }
    }

    /**
     * 重写ThreadPoolExecutor的afterExecute方法,该方法并没有捕获异常，只是获取异常信息；异常线程会继续打印异常堆栈信息
     */
    private static void overrideAfterExecute() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 4, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10), new CustomizableThreadFactory("Wsk-pool-"),
                new ThreadPoolExecutor.CallerRunsPolicy()){
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println(t);
                t.printStackTrace();
            }
        };
        threadPoolExecutor.execute(new WskThreadException());
    }

    /**
     * 测试有捕获异常时的场景
     */
    private static void testCatchException() {
        Thread t = new Thread(new WskThreadException());
        t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(e);
            }
        });
        t.start();
    }

    /**
     * 测试无捕获异常时的场景，无捕获打印异常的全部堆栈信息
     */
    private static void testException() {
        Thread t = new Thread(new WskThreadException());
        t.start();
    }
}
