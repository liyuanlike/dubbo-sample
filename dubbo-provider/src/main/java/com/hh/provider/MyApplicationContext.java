package com.hh.provider;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.hh.facade.SayHelloFacade;
import com.rabbitmq.client.*;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author HaoHao
 * @Description:
 * @date 2018/6/17下午10:43
 */
public class MyApplicationContext implements ApplicationContextInitializer{
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {

        System.out.println("afterPropertiesSet 加载完毕");
        registerMq();
        System.out.println("MQ 注册成功");
    }

    private void registerMq() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(SayHelloFacade.QUEUE_NAME, false, false, false, null);

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String msg = new String(body, "utf-8");
                    System.out.println("接收到消息: " + msg);
                }
            };

            channel.basicConsume(SayHelloFacade.QUEUE_NAME, true, consumer);

        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }





}
