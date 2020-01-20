namespace java com.sinbad.thrift.demo





service HelloWorldService{


    string sayHello(1:string name),

    string sayGoodBye(1:string name)

}