package com.hh.facade;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author HaoHao
 * @Description:
 * @date 2018/6/13下午9:30
 */
public interface SayHelloFacade extends Remote {

    int PORT = 9998;

    String say(String word) throws RemoteException;

}
