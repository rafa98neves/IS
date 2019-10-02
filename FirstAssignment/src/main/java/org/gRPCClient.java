package org;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.grpcFA.HelloReply;
import org.grpcFA.HelloRequest;
import org.grpcFA.OwnerRequestGrpc;

public class gRPCClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8082)
                .usePlaintext()
                .build();


        OwnerRequestGrpc.OwnerRequestBlockingStub stub
                = OwnerRequestGrpc.newBlockingStub(channel);

        HelloReply helloResponse = stub.hello(HelloRequest.newBuilder()
                .setName("Rafa")
                .build());

        channel.shutdown();
    }
}