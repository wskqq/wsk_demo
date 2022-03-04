package example;

import example.exception.WskException;

import java.util.HashMap;
import java.util.Map;

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
    public static void main( String[] args )
    {
        try {
            test();
        } catch (WskException e) {
            e.printStackTrace();
        }
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
}
