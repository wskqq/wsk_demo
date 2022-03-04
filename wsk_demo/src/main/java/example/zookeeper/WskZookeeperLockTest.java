package example.zookeeper;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;

/**
 * zookeeper实现分布式锁
 */
/**
 * @Description TODO
 * @Author acer
 * @Date 2022/3/3 10:39
 * @Version 1.0
 */
public class WskZookeeperLockTest {
    private static final String LOCK_ROOT_PATH = "/WskLocks";

    public static void main(String[] args) {
        try {
            String lockNodePrifix = "wsk_";
            WskZookeeperLock wskZookeeperLock = new WskZookeeperLock();
            try {
                // 加锁
                wskZookeeperLock.acquireLock(LOCK_ROOT_PATH,lockNodePrifix);
                // TODO 业务逻辑


            } catch (KeeperException e) {
                System.out.println(e);
            } catch (InterruptedException e) {
                System.out.println(e);
            }finally {
                try {
                    // 释放锁
                    wskZookeeperLock.releaseLock(lockNodePrifix);
                } catch (KeeperException e) {
                    System.out.println(e);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
