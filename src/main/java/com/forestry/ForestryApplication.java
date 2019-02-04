package com.forestry;

import com.forestry.rpc.TestRpcService;
import com.forestry.rpc.impl.TestRpcServiceImpl;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.forestry.dao")
public class ForestryApplication {
    public static void main(String[] args) {
        SpringApplication.run(ForestryApplication.class, args);
        startRpcServer();
    }

    private static void startRpcServer() {
        try {
            TProcessor tProcessor = new TestRpcService.Processor<TestRpcService.Iface>(new TestRpcServiceImpl());
            TServerSocket serverTransport = new TServerSocket(8080);
            TServer.Args tArgs = new TServer.Args(serverTransport);
            tArgs.processor(tProcessor);
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
            TServer server = new TSimpleServer(tArgs);
            System.out.println("rpc server listen 8080...");
            server.serve();
        }
        catch(TTransportException e) {
            e.printStackTrace();
        }
    }
}

