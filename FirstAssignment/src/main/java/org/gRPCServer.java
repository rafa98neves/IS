package org;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class gRPCServer {
    public static void main(String[] args) {
        Server server = ServerBuilder
                .forPort(8082)
                .addService(new HelloServiceImpl()).build();
        try {
            server.start();
            server.awaitTermination();
        }catch (Exception e){
            System.out.println("Error starting server " + e);
        }
    }
}
