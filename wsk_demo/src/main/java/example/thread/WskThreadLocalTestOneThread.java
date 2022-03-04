package example.thread;
/**
 * @Description TODO
 * @Author acer
 * @Date 2022/3/3 10:39
 * @Version 1.0
 */
public class WskThreadLocalTestOneThread extends Thread {
    Long wsk1;
    WskApp app;

    public void setParam(Long wsk1) {
        this.wsk1 = wsk1;
    }

    public void setWskApp(WskApp app) {
        this.app = app;
    }

    @Override
    public void run() {
        WskApp.threadLocal.set(wsk1);
        System.out.println(WskApp.threadLocal.get());
        System.out.println(WskApp.threadLocal);

        app.map.put("wsk1","wsk1Value");
        System.out.println(app.map);

        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
        }
        System.out.println(WskApp.threadLocal.get());


    }
}