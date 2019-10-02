package org;

import io.grpc.stub.StreamObserver;
import org.grpcFA.Car;
import org.grpcFA.Cars;
import org.grpcFA.OwnerRequestGrpc;
import org.grpcFA.OwnersRequest;

import java.util.ArrayList;

public class RequestServiceImpl extends OwnerRequestGrpc.OwnerRequestImplBase {

    @Override
    public void request(
            OwnersRequest request, StreamObserver<Cars> responseObserver) {

        String ownersList = new StringBuilder()
                .append(request.getOwnersList())
                .toString();

        //Ver carros dos Owners

        //Passar de alguma forma
        ArrayList<Car> CarsList = new ArrayList<>();
        Cars Response = org.grpcFA.Cars.newBuilder()
                .setCars(0, CarsList.get(0))
                .build();

        responseObserver.onNext(Response);
        responseObserver.onCompleted();
    }
}
