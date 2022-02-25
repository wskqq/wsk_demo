package example.thread;

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

        app.threadLocal.set(wsk1);
        System.out.println(app.threadLocal.get());
        System.out.println(app.threadLocal);

        app.map.put("wsk1","wsk1Value");
        System.out.println(app.map);

        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
        }
        System.out.println(app.threadLocal.get());


    }
}