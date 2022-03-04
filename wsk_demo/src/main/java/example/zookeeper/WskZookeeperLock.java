package example.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * zookeeper实现分布式加锁和释放锁
 * 实现原理：
 *  每个客户端往/exlusive_lock下创建有序临时节点/exlusive_lock/lock_。创建成功后/exlusive_lock下面会有每个客户端对应的节点，如/exlusive_lock/lock_000000001
 * 客户端取得/exlusive_lock下子节点，并进行排序，判断排在最前面的是否为自己。
 * 如果自己的锁节点在第一位，代表获取锁成功，此客户端执行业务逻辑
 * 如果自己的锁节点不在第一位，则监听自己前一位的锁节点。例如，自己锁节点lock_000000002，那么则监听lock_000000001.
 * 当前一位锁节点（lock_000000001）对应的客户端执行完成，释放了锁，将会触发监听客户端（lock_000000002）的逻辑。
 * 监听客户端重新执行第2步逻辑，判断自己是否获得了锁
 */
/**
 * @Description TODO
 * @Author acer
 * @Date 2022/3/3 10:39
 * @Version 1.0
 */
public class WskZookeeperLock {
    private ZooKeeper zkClient;
    private String lockPath;
    /**
     * 监控lockPath前一个节点的watcher
     */
    private Watcher watcher = new Watcher() {
        @Override
        public void process(WatchedEvent watchedEvent) {
            System.out.println(watchedEvent.getPath() + "前锁释放");
            // TODO 感觉没有安全问题，不需要加锁
            synchronized (this){
                notifyAll();
            }
        }
    };

    /**
     * 构造器建立连接
     * @throws IOException
     */
    public WskZookeeperLock() throws IOException {
        zkClient = new ZooKeeper("127.0.0.1:2181", 1000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if(watchedEvent.getState() == Event.KeeperState.Disconnected){
                    System.out.println("连接断开了");
                }
            }
        });
    }

    /**
     * 获取锁
     * @param lockRootPath
     * @param lockNodePrifix
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void acquireLock(String lockRootPath, String lockNodePrifix) throws KeeperException, InterruptedException {
        // 创建锁节点
        createLockNode(lockRootPath, lockNodePrifix);
        // 尝试获取锁
        attemptLock(lockRootPath);
    }

    /**
     *  创建锁节点
     * @param lockRootPath
     * @param lockNodePrifix
     * @throws KeeperException
     * @throws InterruptedException
     */
    private void createLockNode(String lockRootPath, String lockNodePrifix) throws KeeperException, InterruptedException {
        Stat stat = zkClient.exists(lockRootPath,false);
        if (stat == null){
            // 创建根节点
            zkClient.create(lockRootPath,new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        }
        // 创建锁节点
        lockPath = zkClient.create(lockRootPath + "/" + lockNodePrifix, Thread.currentThread().getName().getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);

    }

    /**
     * 获取锁
     * @param lockRootPath
     * @throws KeeperException
     * @throws InterruptedException
     */
    private void attemptLock(String lockRootPath) throws KeeperException, InterruptedException {
        List<String> lockPathList = zkClient.getChildren(lockRootPath, false);
        Collections.sort(lockPathList);
        // 获取当前节点的下标
        int index = lockPathList.indexOf(lockPath.substring(lockRootPath.length() + 1));
        if(index == 0){
            System.out.println(Thread.currentThread().getName() + "获得锁" +lockPath);
            return;
        }else{
            // 该节点不是最小的节点，没有获取到锁，需要监控该节点的前一个节点
            String preLockNodePath = lockPathList.get(index-1);
            Stat stat = zkClient.exists(lockRootPath + "/" + preLockNodePath, watcher);
            if (stat == null){
                // 前一个节点不存在了重新获取锁
                attemptLock(lockRootPath);
            }else{
                System.out.println("阻塞当前线程等待锁");
                // TODO 感觉没有安全问题，不需要加锁
                synchronized (watcher){
                    watcher.wait();
                }
                // notifyAll唤醒后重新获取锁
                attemptLock(lockRootPath);
            }
        }
    }

    /**
     * 释放锁
     * @param lockRootPath
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void releaseLock(String lockRootPath) throws KeeperException, InterruptedException {
        zkClient.delete(lockRootPath + "/" + lockPath, -1);
        zkClient.close();
        System.out.println("释放锁并断开连接");
    }
}
