package com.sinbad.thrift.demo;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class ConsumerMain {



    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 8089;
    private static final int TIMEOUT = 30000;

    public static HelloWorldService.Client helloWorldService;

    {
//        String result = helloWorldService.sayHello("sinbad");
//        System.out.println("Thrift client result = " + result);
    }


    /**
     *
     */
    private void startClient() {
        TTransport transport = null;
        try {
            transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
            // 协议要和服务端一致
            TProtocol protocol = new TBinaryProtocol(transport);
            // TProtocol protocol = new TCompactProtocol(transport);
            // TProtocol protocol = new TJSONProtocol(transport);
            helloWorldService = new HelloWorldService.Client(
                    protocol);
            transport.open();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
    }



    public static void main(String[] args) {
        ConsumerMain consumerMain = new ConsumerMain();
        consumerMain.startClient();

    }
}
