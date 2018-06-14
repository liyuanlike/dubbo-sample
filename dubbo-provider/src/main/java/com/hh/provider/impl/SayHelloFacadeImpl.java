package com.hh.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hh.facade.SayHelloFacade;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/**
 * @author HaoHao
 * @Description:
 * @date 2018/6/13下午9:40
 */

@org.springframework.stereotype.Service
public class SayHelloFacadeImpl implements SayHelloFacade ,ApplicationContextAware {



    @Override
    public String say(String word) {

        System.out.println("Hello dubbo~,"+word);

        return "success";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
