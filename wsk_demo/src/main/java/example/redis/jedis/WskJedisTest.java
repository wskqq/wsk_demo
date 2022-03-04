package example.redis.jedis;

import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.UUID;

/**
 * 使用redis实现分布式锁
 * 问题：
 * 1.没有考虑业务逻辑执行过长导致锁超时，其他线程获取的锁情况，可以通过锁延期解决
 * 2.主从情况下，主节点加锁成功，还未同步其他从节点之前，服务挂掉，导致其他线程可以再次获取锁，
 *   通过redisson的redlock红锁解决
 * 并发要求不高的情况下：以上两个问题可以通过人工补偿机制解决
 */
/**
 * @Description TODO
 * @Author acer
 * @Date 2022/3/3 10:39
 * @Version 1.0
 */
public class WskJedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1:6379");
        String nxxx = "NX";
        String expx = "PX";
        String lockSuccessCode = "OK";
        Object unlockSuccessCode = 1;
        int expireTime = 10000;
        String keyPrifix = "wsk:";
        String wskKey = keyPrifix + "wskKey";
        String wskValue = UUID.randomUUID().toString().replace("-","");
        String unLockLua = "if redis.call('get',KEYS[1]) == ARGV[1] then " +
                "return redis.call('del',KEYS[1]) else return 0 end";
        // 获取锁
        int cyclesNum = 10;
        for(int i=0; i<cyclesNum; i++){
            // 获取锁
            String lockResult = jedis.set(wskKey, wskValue, nxxx,expx,expireTime);
            if(lockSuccessCode.equals(lockResult)){
                //  TODO 具体业务逻辑

                // 释放锁
                Object unlockResult = jedis.eval(unLockLua, Collections.singletonList(wskKey), Collections.singletonList(wskValue));
                if(unlockSuccessCode.equals(unlockResult)){
                    System.out.println("释放锁成功");

                }else{
                    System.out.println("释放锁失败");
                }
                return;
            }else{
                try {
                    Thread.sleep(50L);
                } catch (InterruptedException e) {
                    System.out.println("获取锁失败之后休眠50ms，避免连续获取锁导致cpu过高");
                }
                continue;
            }


        }
    }
}
