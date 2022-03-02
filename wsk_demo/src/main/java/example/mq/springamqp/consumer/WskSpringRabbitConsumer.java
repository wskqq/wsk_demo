package example.mq.springamqp.consumer;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * spring整合rabbitmq消费者
 */
public class WskSpringRabbitConsumer {
    public static void main(String[] args) {
        String ip = "127.0.0.1:5672";
        String userName = "wsk";
        String pwd = "wskPwd";
        String exchange = "WskExchange";
        String routingKey = "WskRoutingKey";
        String msgQueue = "WskQueue";
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setAddresses(ip);
        cachingConnectionFactory.setUsername(userName);
        cachingConnectionFactory.setPassword(pwd);

        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.receiveAndConvert(msgQueue,10000);
    }
}
