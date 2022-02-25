package example.thread;

public class WskThreadLocalTestTwoThread extends Thread{
    Long wsk2;
    WskApp app;
    public void setParam(Long wsk2){
        this.wsk2 = wsk2;
    }
    public void setWskApp(WskApp app){
        this.app = app;
    }
    @Override
    public void run() {
        app.threadLocal.set(wsk2);
        System.out.println(app.threadLocal.get());
        System.out.println(app.threadLocal);

        app.map.put("wsk2","wsk2Value");
        System.out.println(app.map);
    }
}
