package com.dubbo.consumer.service.impl;

import com.dubbo.consumer.service.BaseService;

import org.springframework.stereotype.Service;

/**
 * @author HaoHao
 * @Description:
 * @date 2018/6/15下午11:25
 */
@Service
public class BaseServiceImpl implements BaseService{
    @Override
    public void doSomething() {
        System.out.println("doing~");
    }
}
