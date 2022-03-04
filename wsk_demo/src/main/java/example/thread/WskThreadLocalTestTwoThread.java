package example.thread;

import java.util.Objects;

/**
 * @Description TODO
 * @Author acer
 * @Date 2022/3/3 10:39
 * @Version 1.0
 */
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
        WskApp.threadLocal.set(wsk2);
        System.out.println(WskApp.threadLocal.get());
        System.out.println(WskApp.threadLocal);

        app.map.put("wsk2","wsk2Value");
        System.out.println(app.map);
    }
}
