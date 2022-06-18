package example.mail;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * @Description TODO
 * @Author wsk
 * @Date 2022/6/17 18:00
 * @Version 1.0
 */
public class MailUtil {
    private static Log log = LogFactory.getLog(MailUtil.class);

    public static void main(String [] args) {
        // 收件人电子邮箱
        String to = "xxx@qq.com";

        // 发件人电子邮箱
        String from = "xxx@qq.com";

        // 指定发送邮件的主机为 smtp.qq.com
        String host = "smtp.qq.com";  //QQ 邮件服务器

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);

        properties.put("mail.smtp.auth", "true");
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties,new Authenticator(){
            public PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("xxx@qq.com", "qq邮箱授权码"); //发件人邮件用户名、授权码
            }
        });

        try{
            // 创建默认的 MimeMessage 对象。
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: 头字段
            message.setSubject("This is the Subject Line!");

            // 创建消息部分
            BodyPart messageBodyPart = new MimeBodyPart();

            // 消息
            messageBodyPart.setText("This is message body");

            // 创建多重消息
            Multipart multipart = new MimeMultipart();

            // 设置文本消息部分
            multipart.addBodyPart(messageBodyPart);

            // 附件部分
            messageBodyPart = new MimeBodyPart();
            String filename = "file.txt";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            // 设置非中文名
            // messageBodyPart.setFileName(filename);
            // 设置含有中文的名称
            try {
                messageBodyPart.setFileName(MimeUtility.encodeWord(filename));
            } catch (UnsupportedEncodingException e) {
                log.info("转换附件名失败", e);
            }
            multipart.addBodyPart(messageBodyPart);

            // 发送完整消息
            message.setContent(multipart);

            // 对于整合OSGI框架时报错：javax.activation.UnsupportedDataTypeException:
            // MIME类型multipart/mixed没有对象DCH的解决方案
//            Thread.currentThread().setContextClassLoader(Message.class.getClassLoader());

            // 发送消息
            Transport.send(message);
            System.out.println("Sent message successfully....");
        }catch (MessagingException e) {

            log.info("邮件发送失败", e);
        }
    }
}
