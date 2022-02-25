package example.redis.redisson;


import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * 通过redisson实现布隆过滤器
 */
public class WskRedissonTest {
    public static void main(String[] args) {
        Config config = new Config();
        /**
         * 两种方式中有一种可行，待验证
         * 设置redis地址配置，创建Redisson客户端对象
         */
//        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        config.useSingleServer().setAddress("127.0.0.1:6379");
        RedissonClient redissonClient = Redisson.create(config);

        // 根据redisson客户端对象获取布隆过滤器对象
        RBloomFilter<Object> bloomFilter= redissonClient.getBloomFilter("sample");

        // 设置布隆过滤器基数及容错率
        bloomFilter.tryInit(1000000L,0.03);

        // 向布隆过滤器中添加数据
        bloomFilter.add("wsk");

        // 验证数据是否在布隆过滤器中
        System.out.println(bloomFilter.contains("test"));
        System.out.println(bloomFilter.contains(10));
        System.out.println(bloomFilter.contains("wsk"));
    }
}
