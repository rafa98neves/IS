package org;

import java.util.List;
import io.grpc.stub.StreamObserver;
import org.grpcFA.*;

public class RequestServiceImpl extends OwnerRequestGrpc.OwnerRequestImplBase {

    @Override
    public void request(OwnersRequest request, StreamObserver<Cars> responseObserver) {

        String ownersList = new StringBuilder()
                .append(request.getOwnersList())
                .toString();

        System.out.println(ownersList);

        /*Ver carros dos Owners*/


        Repository repo = new Repository();
        List<Car> cars = repo.getCars();
        int counter = 0;
        Cars.Builder Car_builder = Cars.newBuilder();
        for(Car c : cars){
            Car_builder.setCars(counter,c);
            counter++;
        }
        Cars Response = Car_builder.build();

        responseObserver.onNext(Response);
        responseObserver.onCompleted();
    }
}
