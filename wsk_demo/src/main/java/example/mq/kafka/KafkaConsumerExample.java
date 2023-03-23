package example.mq.kafka;


import org.apache.kafka.clients.consumer.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;
import java.util.Properties;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

/**
 * @Description kafka消费者客户端示例
 * @Author wsk
 * @Date 2023/3/23 14:55
 * @Version 1.0
 */
public class KafkaConsumerExample {

    public static void main(String[] args) {

        // 设置Kafka消费者客户端的配置属性
        Properties props = new Properties();
        // Kafka集群的地址列表，客户端会根据这个地址列表把消息发给对应的broker。
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // 消费者所属的消费组ID。同一个消费组内的多个消费者共同消费一个或多个主题下的所有消息。
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // auto.offset.reset：当消费者消费消息时没有找到合适的偏移量时，该属性指定了该如何处理。最常见的有两种选项：earliest（从最早的偏移量开始）和latest（从最新的偏移量开始）。
        // enable.auto.commit：是否启用自动提交偏移量。如果启用，消费者会定期自动提交偏移量。如果禁用，则需要手动提交偏移量，可以确保消息已被完整地处理。
        // fetch.min.bytes和fetch.max.wait.ms：控制消费者从broker中读取数据的速度。fetch.min.bytes控制每次拉取数据的最小字节数，fetch.max.wait.ms控制等待时间，当从broker上没有读取到数据时，消费者会等待一些时间再去拉取数据。
        // max.poll.records：每次拉取数据时最多拉取的记录数，该属性可以控制消费者单次拉取数据的频率。
        // isolation.level：该属性控制消费者读取消息的隔离级别。可以设置为read_committed或read_uncommitted，前者只会读取已提交的消息，后者会读取未提交的和已提交的消息。

        // 创建Kafka消费者客户端实例
        Consumer<String, String> consumer = new KafkaConsumer<>(props);

        // 订阅主题
        consumer.subscribe(Arrays.asList("test_topic"));

        // 循环接收消息
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("Received message: " + record.value());
            }
        }
    }
}


