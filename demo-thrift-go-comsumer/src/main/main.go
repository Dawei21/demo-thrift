package main

import (
	"context"
	"demo-thrift-client/com/sinbad/thrift/demo"
	"fmt"
	"github.com/apache/thrift/lib/go/thrift"
	"net"
	"os"
	"time"
)

func main() {

	fmt.Println("Start ....")

	tBufferedTransportFactory := thrift.NewTBufferedTransportFactory(8192)
	newTSocket, err := thrift.NewTSocket(net.JoinHostPort("127.0.0.1", "8089"))
	if err != nil {
		fmt.Print("Error to create socket, err=", err)
		os.Exit(1)
	}

	tTransport, err := tBufferedTransportFactory.GetTransport(newTSocket)

	if err:= newTSocket.Open(); err != nil {
		fmt.Fprintln(os.Stderr, "Error open socket to ...",  err)
		os.Exit(1)
	}

	protocolTransport := thrift.NewTBinaryProtocolFactoryDefault()
	serviceClientFactory := demo.NewHelloWorldServiceClientFactory(tTransport, protocolTransport)
	defer newTSocket.Close()

	nowTime := time.Now()
	format := nowTime.Format("2006-01-02 15:04:05")
	fmt.Println("format : " , format)
	cancelContext, cancelFunc := context.WithDeadline(context.Background(), nowTime)

	sayHello, err := serviceClientFactory.SayHello(cancelContext ,"Sinbad")
	if err != nil {
		fmt.Fprintln(os.Stderr, "Client Say Hello failed, err = ",  err)
		os.Exit(1)
	}

	deadline, autoCancel := cancelContext.Deadline()
	//非自动取消
	if !autoCancel {
		//取消执行
		cancelFunc()
		fmt.Println("autoCancel ")
	} else {
		fmt.Println(deadline.Format("2006-01-02 15:04:05"))
	}


	fmt.Print("Say hello result : ", sayHello)



}
