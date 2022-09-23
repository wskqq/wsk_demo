package example;

import example.exception.WskException;
import org.springframework.util.ReflectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.Thread.*;

/**
 * @Description 测试
 * @Author acer
 * @Date 2022/3/3 10:39
 * @Version 1.0
 */
public class App
{
//    static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();
    Map<String,String> map = new HashMap<String,String>();
    public static void main( String[] args ) throws InterruptedException {
//        AtomicReference<String> arf = new AtomicReference<>();
//        String str = arf.get();
//        ThreadPoolExecutor
//        ScheduledExecutorService
//        AbstractExecutorService
//        ExecutorService
//        Executor
//        ThreadPoolExecutor
//        CountDownLatch countDownLatch = new CountDownLatch(1);
        // Semphore常用方法
//        Semaphore semaphore = new Semaphore(10);
//        semaphore.availablePermits();
//        semaphore.tryAcquire();
//        semaphore.acquire();
//        semaphore.release();

        // CyclicBarrier常用方法
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(1);

        // ForkJoinPool常用方法
//        ForkJoinPool forkJoinPool = new ForkJoinPool();
//        RecursiveAction
//        forkJoinPool.submit();
//        forkJoinPool.awaitTermination();
        // 有界队列
//        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(16);
//        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue<String>(16);

        // 无界队列
//        PriorityBlockingQueue priorityQueue = new PriorityBlockingQueue();
//        DelayQueue delayQueue = new DelayQueue();

//          testExecutorService();

        testThreadPoolExecutor();

//          testThread();
//        try {
//            test();
//        } catch (WskException e) {
//            e.printStackTrace();
//        }
//        threadLocal.remove();
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WskConfig.class);
//        WskUser wskUser = (WskUser) context.getBean("WskUser");
//        System.out.println(wskUser.toString());
//        WskController wskController = (WskController) context.getBean("wskController");
//        System.out.println(wskController);
//        System.out.println( "Hello World!" );

//        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
//        try {
//            Scheduler scheduler = schedulerFactory.getScheduler();
//            JobDetail jobDetail = JobBuilder.newJob().withIdentity("","").build();
//            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("","")
//                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3)
//                            .repeatForever()).build();
//            scheduler.scheduleJob(jobDetail,trigger);
//            scheduler.start();
//        } catch (SchedulerException e) {
//        }
//        Runnable r1 = new Runnable() {
//            @Override
//            public void run() {
//                App app = new App();
//                app.threadLocal.set(100L);
//                app.threadLocal.get();
//                System.out.println(app.threadLocal.get());
//                System.out.println(app.threadLocal);
//            }
//        };
//        r1.run();
//
//        Runnable r2 = new Runnable() {
//            Long r2L;
//            @Override
//            public void run() {
//                App app = new App();
//                app.threadLocal.set(r2L);
//                app.threadLocal.get();
//                System.out.println(app.threadLocal.get());
//                System.out.println(app.threadLocal);
//            }
//            public void setParam(){
//                r2L = 10L;
//            }
//        };
//        Thread t2 = new Thread(r2);
//        t2.start();

    }
    public static void test() throws WskException {
        throw new WskException("测试异常");
    }

    /**
     * 创建单个线程的线程池，使用完立即销毁
     */
    private static void testExecutorService() {
        Long startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new WskRunable());
        executorService.shutdown();

        Long endTime = System.currentTimeMillis();
        System.out.println("单个线程的线程池程序运行时间[" + (endTime-startTime) + "]");
    }


    private static ThreadPoolExecutor threadPoolExecutor1 = new ThreadPoolExecutor(2,3,
            30,TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), new ThreadPoolExecutor.CallerRunsPolicy());
    public static ThreadPoolExecutor getThreadPoolExecutor(){
        threadPoolExecutor1.allowCoreThreadTimeOut(true);
        return threadPoolExecutor1;
    }
    /**
     * 测试线程池核心线程数超时关闭
     */
    private static void testThreadPoolExecutor() {
        Long startTime = System.currentTimeMillis();
        ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();
        threadPoolExecutor.execute(new WskRunable());
        threadPoolExecutor.execute(new WskRunable());
        threadPoolExecutor.execute(new WskRunable());
        threadPoolExecutor.execute(new WskRunable());
        System.out.println("超时之前线程排队数[" + threadPoolExecutor.getQueue().size() + "],当前活动线程数[" +
                threadPoolExecutor.getActiveCount() + "],CPU核数[" +
                Runtime.getRuntime().availableProcessors() + "],线程池大小[" +
                threadPoolExecutor.getPoolSize() + "],核心线程数[" + threadPoolExecutor.getCorePoolSize() + "]");
//        executorService.shutdown();
        try {
            Thread.sleep(60000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("超时之后线程排队数[" + threadPoolExecutor.getQueue().size() + "],当前活动线程数[" +
                    threadPoolExecutor.getActiveCount() + "],CPU核数[" +
                Runtime.getRuntime().availableProcessors() + "],线程池大小[" +
                threadPoolExecutor.getPoolSize() + "],核心线程数[" + threadPoolExecutor.getCorePoolSize() + "]");

        try {
            Thread.sleep(60000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        threadPoolExecutor.execute(new WskRunable());
        System.out.println("最后线程排队数[" + threadPoolExecutor.getQueue().size() + "],当前活动线程数[" +
                threadPoolExecutor.getActiveCount() + "],CPU核数[" +
                Runtime.getRuntime().availableProcessors() + "],线程池大小[" +
                threadPoolExecutor.getPoolSize() + "],核心线程数[" + threadPoolExecutor.getCorePoolSize() + "]");
        Long endTime = System.currentTimeMillis();
        System.out.println("单个线程的线程池程序运行时间[" + (endTime-startTime) + "]");
    }

    /**
     * 单独创建线程
     */
    private static void testThread() {
        Long startTime = System.currentTimeMillis();
        new Thread(new WskRunable()).start();

        Long endTime = System.currentTimeMillis();
        System.out.println("单个线程的程序运行时间[" + (endTime-startTime) + "]");
    }

    public static class WskRunable implements Runnable{

        @Override
        public void run() {
            Long startTime = System.currentTimeMillis();
            try {
                sleep(5000L);
            } catch (InterruptedException e) {
                System.out.println("线程休眠失败");
            }
            int n = 0;
            for(;;){
                n++;

                if(n>1000){
                    break;
                }
            }
            Long endTime = System.currentTimeMillis();
            System.out.println("执行线程任务完成时间[" + (endTime-startTime) + "]");
        }
    }
}
