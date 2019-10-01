package org.grpcFA;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Client {

    public static void main(String[] args) throws Exception {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        OwnerRequest.HelloServiceBlockingStub stub
                = HelloServiceGrpc.newBlockingStub(channel);

        HelloReply helloResponse = stub.hello(HelloRequest.newBuilder()
                .setFirstName("Rafel")
                .setLastName("gRPC")
                .build());

        channel.shutdown();
    }

}