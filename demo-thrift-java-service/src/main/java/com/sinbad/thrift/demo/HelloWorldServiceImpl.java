package com.sinbad.thrift.demo;

import org.apache.thrift.TException;

public class HelloWorldServiceImpl implements HelloWorldService.Iface{


    public HelloWorldServiceImpl() {

    }

    public String sayHello(String name) throws TException {
        System.out.println("Say hello to " + name);
        return "Are you ok, " + name + " ?";
    }





    public String sayGoodBye(String name) throws TException {
        System.out.println("Say good bye to " + name);
        return "Good bye, " + name;
    }
}
