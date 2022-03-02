package example.mq.rabbitmq.consumer;

import com.rabbitmq.client.*;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * rabbitMQ作为消费者
 * 没有整合spring
 */
public class WskRabbitMQConsumer {
    public static void main(String[] args) {
        String ip = "127.0.0.1";
        int port = 5672;
        String userName = "wsk";
        String pwd = "wskPwd";
        String msgQueue = "WskQueue";
        Address[] addresses = new Address[]{new Address(ip,port)};
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername(userName);
        connectionFactory.setPassword(pwd);
        try {
            // 自定义线程池  拒绝策略为：队列已满，线程数已达到核心线程数，再接收的请求不在由线程池处理该由主线程处理
            int corePoolSize = 1;
            int maximumPoolSize = 1;
            long keepAliveTime = 10L;
            TimeUnit unit = TimeUnit.SECONDS;
            BlockingDeque<Runnable> workQueue = new LinkedBlockingDeque<Runnable>(5);
            // 线程工厂创建方式一： 利用spring提供的方法
//            ThreadFactory threadFactory = new CustomizableThreadFactory("wsk-pool-");
            // 方式二：使用google提供的guava提供的ThreadFactoryBuilder给线程中的线程设置自定义的线程名
//            ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("wsk-pool-").build();
            // 方式三：通过apache common-lang3的BasicThreadFactory设置
            ThreadFactory threadFactory = new BasicThreadFactory.Builder().namingPattern("wsk-pool-").build();

            ExecutorService executorService = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,
                    keepAliveTime,unit,workQueue,threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
            // 使用消息队列线程池时，使用完需要主动调用shutdown方法，调用该方法后不再接收新请求，处理完成后关闭连接池
//            executorService.shutdown();
            // 创建连接
//            Connection connection = connectionFactory.newConnection(executorService,addresses);
            Connection connection = connectionFactory.newConnection(addresses);
            // 创建信道
            Channel channel = connection.createChannel();
            // 设置客户端未被ack的消息的个数
            // 该消费者在接收到队列里的消息但没有返回确认结果之前,队列不会将新的消息分发给该消费者。
            // 队列中没有被消费的消息不会被删除，还是存在于队列中。64表示最大接受64个未响应的ack
//            channel.basicQos(64);
            channel.basicQos(1);
            Consumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println("接收到的消息：" + new String(body));
                    // 消费消息

                    // 消费完成响应  第二个参数：确认一条消息还是多条.false 表示只确认 e.DelivertTag 这条消息,
                    // true表示确认 小于等于 e.DelivertTag 的所有消息
                    String routingKey = envelope.getRoutingKey();
                    String contentType = properties.getContentType();
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            };
            // 监控消息队列，有消息时通过回调处理消息, 第二个参数：是否自动确认，默认false，
            channel.basicConsume(msgQueue,false,consumer);
            try {
                // 休眠5s
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 关闭连接
            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
