package example.mq.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * rabbitMQ生产者,可支持事务消息  TX事务模式
 * Confirm确认模式
 */
public class WskRabbitMQProducer {
    public static void main(String[] args) {
        String ip = "127.0.0.1";
        int port = 5672;
        String userName = "wsk";
        String pwd = "wskPwd";
        String exchange = "WskExchange";
        String routingKey = "WskRoutingKey";
        String msgQueue = "WskQueue";
        // 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 设置rabbitMQ的相关信息
        connectionFactory.setHost(ip);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(userName);
        connectionFactory.setPassword(pwd);
        Connection connection = null;
        Channel channel = null;
        try {
            //创建一个新连接
            connection = connectionFactory.newConnection();
            // 创建一个通道
            channel = connection.createChannel();
            // 创建一个type=direct的持久化的非自动删除的交换器
            // 参数含义：交换器名称、 交换器类型（direct/fanout/topic/headers）、 是否持久化、
            // 不在使用时是否删除交换器、交换器的其他属性
            channel.exchangeDeclare(exchange,"direct",true,false,null);
            // 创建一个持久的非排他的非自动删除的队列
            // 参数含义：队列名称、是否持久化、是否为独占队列、不在使用时是否自动删除队列、队列其他参数
            channel.queueDeclare(msgQueue,true,false,false,null);
            // 将队列与交换器通过路由绑定
            channel.queueBind(msgQueue,exchange,routingKey);
            String sendMsg = "Hello Wsk 测试";

            // 方式一：tx模式实现事务时需要添加的代码
//            channel.txSelect();
            // 方式二：Confirm模式 开启事务
            channel.confirmSelect();

            // 发送消息到队列中
            // MessageProperties.PERSISTENT_TEXT_PLAIN(持久化) 设置消息类型、优先级及是否持久化
            channel.basicPublish(exchange,routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN,sendMsg.getBytes("UTF-8"));

            // 方式一：事务消息提交
//            channel.txCommit();
            try {
                // 方式二：
                if(channel.waitForConfirms()){
                    System.out.println("事务执行成功");
                }else{
                    System.out.println("事务执行失败");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            // 方式一：回滚事务消息
//            try {
//                channel.txRollback();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally {
            // 关闭通道及连接
            if(channel != null){
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
