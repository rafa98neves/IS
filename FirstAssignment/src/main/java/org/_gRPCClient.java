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
        repo.GenerateOwners(18000);
        List<Owner> owners = repo.getOwners();

        /* Send to Server*/
        long time = System.currentTimeMillis();
        OwnersRequest.Builder Owner_builder = OwnersRequest.newBuilder();
        for(Owner o : owners){
            Owner_builder.addOwners(o);
        }

        /* Get response and shutdown */
        Ownerships_list response = stub.request(Owner_builder.setTimeRequest(time).build());
        long timer = System.currentTimeMillis() - response.getTimeReply() + response.getTimeRequest();
        System.out.println(timer);

        channel.shutdown();
        try{
            channel.awaitTermination(5, TimeUnit.SECONDS);
        }catch(Exception e){
            channel.shutdownNow();
        }
    }
}