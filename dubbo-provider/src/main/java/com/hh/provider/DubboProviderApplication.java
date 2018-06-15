package com.hh.provider;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import com.hh.facade.SayHelloFacade;
import com.hh.provider.impl.SayHelloFacadeImpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
public class DubboProviderApplication {


    private static void exportRMI() throws RemoteException {
        SayHelloFacade sayHelloFacade = new SayHelloFacadeImpl();
        Remote remote = UnicastRemoteObject.exportObject(sayHelloFacade, SayHelloFacade.PORT);
        Registry registry = LocateRegistry.createRegistry(SayHelloFacade.PORT);
        try {
            registry.bind(SayHelloFacade.class.getName(), remote);
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws RemoteException {
        SpringApplication.run(DubboProviderApplication.class, args);
        exportRMI();
    }
}
