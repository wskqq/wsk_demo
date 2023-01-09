package example.ehcache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.ResourcePools;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;

import java.time.Duration;

/**
 * @Description 方式一：通过纯代码方式使用ehcache缓存；
 *              方式二：还可以通过xml配置文件形式使用
 * @Author wsk
 * @Date 2023/1/5 10:22
 * @Version 1.0
 */
public class EhcacheTest1 {

    public static void main(String[] args) {
        // 创建缓存管理对象
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .with(CacheManagerBuilder.persistence("D:\\test\\myehcache\\"))
                // 初始化方式1：默认false，设置true会进行初始化
                .build(true);

        // 配置缓存存储形式
        ResourcePools resourcePools = ResourcePoolsBuilder.newResourcePoolsBuilder()
                // 堆内缓存 只存储一个字符串时，最小设置为272，否则存储失败，不能设置过小
                .heap(272L, MemoryUnit.B)
                // 堆外缓存
//                .offheap(10L, MemoryUnit.B)
                // 磁盘缓存
//                .disk(1L, MemoryUnit.MB)
                .build();

        // 封装缓存存储对象，设置缓存过期时间
        CacheConfiguration<Integer, String> cacheConfiguration = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(Integer.class, String.class, resourcePools)
                .withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofSeconds(10)))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(5)))
                .build();

        // 初始化方式二：需要初始化，否则，执行报错 State is UNINITIALIZED, 也可以在CacheManagerBuilder的build(true)也会初始化
//        cacheManager.init();

        // 根据配置文件cacheConfiguration，创建指定名称的缓存
        Cache<Integer, String> cache = cacheManager.createCache("myehcache", cacheConfiguration);
        // 测试使用缓存
        cache.put(1, "测");
        String cacheV = cache.get(1);
        System.out.println("缓存key=1>" + cacheV);
        cache.put(2, "23456");

        System.out.println("缓存key=1>" + cacheV);
        System.out.println("缓存key=2>" + cache.get(2));

        // 测试超过过期时间TTL后缓存是否清空
//        try {
//            Thread.sleep(10000L);
//        } catch (InterruptedException e) {
//            Thread.interrupted();
//        }
//        cacheV = cache.get(1);
//        System.out.println(cacheV);
//        System.out.println(cache.get(2));
    }
}
