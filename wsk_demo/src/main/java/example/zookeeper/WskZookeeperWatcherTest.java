package example.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * zookeeper测试类
 * watcher机制，watcher都是一次性的，需要持续监控的时候，需要重新设置
 */
/**
 * @Description TODO
 * @Author acer
 * @Date 2022/3/3 10:39
 * @Version 1.0
 */
public class WskZookeeperWatcherTest {
    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            final ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182",
                    30000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("监听事件路径：" + watchedEvent.getPath());
                    System.out.println("监听事件类型：" + watchedEvent.getType());
                    System.out.println("监听事件状态：" + watchedEvent.getState());
                    if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
                        countDownLatch.countDown();
                    }
                }
            });
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                System.out.println("异常报错2");
            }

            try {
                // ZooDefs.Ids的属性表示该节点的权限；CreateMode的属性表示节点类型；
                zooKeeper.create("/wsk-first", "wskdata".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            } catch (KeeperException e) {
            } catch (InterruptedException e) {
                System.out.println("create异常");
            }

            try {
                zooKeeper.exists("/wsk-first", new Watcher() {
                    @Override
                    public void process(WatchedEvent watchedEvent) {
                        System.out.println("监听节点状态变化");
                    }
                });
            } catch (KeeperException e) {
                System.out.println("exists方法报错：" + e);
            } catch (InterruptedException e) {
                System.out.println("exists方法报错：" + e);
            }
        } catch (IOException e) {
            System.out.println("异常报错1");
        }

    }
}
