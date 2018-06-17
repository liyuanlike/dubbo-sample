package com.hh.provider.impl;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.alibaba.dubbo.config.annotation.Service;
import com.hh.facade.SayHelloFacade;
import com.rabbitmq.client.*;

import org.springframework.beans.factory.InitializingBean;


/**
 * @author HaoHao
 * @Description:
 * @date 2018/6/13下午9:40
 */

@Service
public class SayHelloFacadeImpl implements SayHelloFacade{

    @Override
    public String say(String word) {

        System.out.println("Hello dubbo~," + word);
        return "success";
    }


}
