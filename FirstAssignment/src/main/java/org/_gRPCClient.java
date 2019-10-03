package org;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.grpcFA.*;


public class _gRPCClient {
    public static void main(String[] args){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8082)
                .usePlaintext()
                .build();

        OwnerRequestGrpc.OwnerRequestBlockingStub stub
                = OwnerRequestGrpc.newBlockingStub(channel);

        /*Generate Owners*/
        _Repository repo = new _Repository();
        repo.GenerateOwners(3);
        List<Owner> owners = repo.getOwners();
        for(Owner o : owners){
            System.out.println(o.getName());
        }

        /* Send to Server*/
        OwnersRequest.Builder Owner_builder = OwnersRequest.newBuilder();
        for(Owner o : owners){
            Owner_builder.addOwners(o);
        }

        /* Get response and shutdown */
        Ownerships_list response = stub.request(Owner_builder.build());

        System.out.println(response);
        channel.shutdown();
        try{
            channel.awaitTermination(5, TimeUnit.SECONDS);
        }catch(Exception e){
            channel.shutdownNow();
        }
    }
}