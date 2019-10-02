package org;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.grpcFA.Cars;
import org.grpcFA.Owner;
import org.grpcFA.OwnerRequestGrpc;
import org.grpcFA.OwnersRequest;

import java.util.ArrayList;

public class gRPCClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8082)
                .usePlaintext()
                .build();


        OwnerRequestGrpc.OwnerRequestBlockingStub stub
                = OwnerRequestGrpc.newBlockingStub(channel);

        Repository repo = new Repository();

        ArrayList<Owner> owners = repo.getOwners();
        Cars CarsResponse = stub.request(OwnersRequest.newBuilder()
                .setOwners()
                .build());

        channel.shutdown();
    }
}