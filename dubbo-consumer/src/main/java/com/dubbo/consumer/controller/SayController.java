package com.dubbo.consumer.controller;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hh.facade.SayHelloFacade;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
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

}
