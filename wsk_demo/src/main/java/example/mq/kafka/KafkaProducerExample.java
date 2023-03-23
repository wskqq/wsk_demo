package example.mq.kafka;

import org.apache.kafka.clients.producer.*;
import java.util.Properties;

/**
 * @Description kafka生产者客户端示例
 * @Author wsk
 * @Date 2023/3/23 14:54
 * @Version 1.0
 */
public class KafkaProducerExample {
    public static void main(String[] args) {
        Properties props = new Properties();
        // 指定Kafka集群中用于初始化的主机地址和端口号。
        // 生产者在向Kafka集群发送数据时，需要知道Kafka集群的地址信息。
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // 指定生产者需要收到多少个ack确认消息后才能认为消息发送成功。可选值包括“0”、“1”、“all”等。
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        // 指定生产者在发送消息失败后，重试的最大次数。
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        // 指定生产者在缓冲区中积累的最大消息数量。
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        // 指定生产者在等待更多消息积累到缓冲区中的时间阈值，单位是毫秒。
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        // 指定生产者用于缓存消息的内存大小。
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        // 指定生产者发送给Kafka集群的消息的键和值的序列化器。
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        // compression.type：指定生产者发送给Kafka集群的消息是否需要进行压缩。可选值包括“none”、“gzip”、“snappy”等。

        Producer<String, String> producer = new KafkaProducer<>(props);
        String topic = "test_topic";
        for (int i = 0; i < 10; i++) {
            String message = "message " + i;
            producer.send(new ProducerRecord<>(topic, message));
        }
        producer.close();
    }
}

