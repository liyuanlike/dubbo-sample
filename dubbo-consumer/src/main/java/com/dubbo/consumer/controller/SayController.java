package com.dubbo.consumer.controller;

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
public class SayController  implements BeanFactoryAware,ApplicationContextAware,InitializingBean{

    @Reference(url = "dubbo://localhost:12345",
    timeout = 10000)
    private SayHelloFacade sayHelloFacade;


    @RequestMapping("say")
    public String say() {
        String str = sayHelloFacade.say("I am Ma Yun papa");
        return str;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        
    }
}
