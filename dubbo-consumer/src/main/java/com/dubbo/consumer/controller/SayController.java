package com.dubbo.consumer.controller;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.TimeoutException;

import com.dubbo.consumer.service.BaseService;
import com.hh.facade.SayHelloFacade;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author HaoHao
 * @Description:
 * @date 2018/6/13下午9:54
 */
@RestController
public class SayController {


    //    @Reference(url = "dubbo://localhost:12345",
//    timeout = 10000)
    private SayHelloFacade sayHelloFacade;

    @Autowired
    private BaseService baseService;

    @RequestMapping("say")
    public String say() throws RemoteException {
        //RMI
        Registry registry = LocateRegistry.getRegistry(SayHelloFacade.PORT);

        try {
            sayHelloFacade = (SayHelloFacade) registry.lookup(SayHelloFacade.class.getName());
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

        String str = sayHelloFacade.say("I am Ma Yun papa");
        return str;
    }

    @RequestMapping("doSomething")
    public String doSomething() {
        baseService.doSomething();
        return "success";
    }

    @RequestMapping("sendMqMsg")
    public String msg(String msg) {
        try {
            sendMsg(msg);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        return "success";
    }

    private void sendMsg(String str) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(SayHelloFacade.MQ_PORT);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(SayHelloFacade.QUEUE_NAME, false, false, false, null);
        channel.basicPublish("",SayHelloFacade.QUEUE_NAME , null, str.getBytes("utf-8"));
        channel.close();
        connection.close();
    }
}
