namespace go com.sinbad.thrift.demo


//服务端口号
const i32 SERVICE_PORT = 8089;



service HelloWorldService{


    string sayHello(1:string name),

    string sayGoodBye(1:string name)

}