package com.sinbad.thrift.demo;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

import java.io.IOException;

public class ServiceDemoMain {


    private static final int SERVICE_PORT = 8089;


    public static void main(String[] args) throws IOException {
        ServiceDemoMain serviceDemoMain = new ServiceDemoMain();
        serviceDemoMain.startService();
    }


    public void startService() throws IOException {
        System.out.println("Begin to start hello world demo ------");

        try {
            TProcessor processor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldServiceImpl());


            TServerSocket tServerSocket  = new TServerSocket(SERVICE_PORT);

            TServer.Args args = new TServer.Args(tServerSocket);
            args.processor(processor);

            args.protocolFactory(new TBinaryProtocol.Factory());

            TServer server = new TSimpleServer(args);

            server.serve();
            System.out.println("server  serve finish");
        } catch (TTransportException e) {
            e.printStackTrace();
        }

        System.out.println("read   finish");
        System.in.read();
    }

}
