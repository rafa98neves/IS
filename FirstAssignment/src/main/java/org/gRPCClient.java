package org;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.grpcFA.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


public class gRPCClient {
    public static void main(String[] args){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8082)
                .usePlaintext()
                .build();

        OwnerRequestGrpc.OwnerRequestBlockingStub stub
                = OwnerRequestGrpc.newBlockingStub(channel);

        Repository repo = new Repository();
        List<Owner> owners =  repo.getOwners();

        OwnersRequest.Builder Owner_builder = OwnersRequest.newBuilder();
        for(Owner o : owners){
            Owner_builder.addOwners(o);
        }
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