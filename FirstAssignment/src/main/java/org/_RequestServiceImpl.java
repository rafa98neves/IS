package org;

import java.util.List;
import io.grpc.stub.StreamObserver;
import org.grpcFA.*;

public class _RequestServiceImpl extends OwnerRequestGrpc.OwnerRequestImplBase {

    @Override
    public void request(OwnersRequest request, StreamObserver<Ownerships_list> responseObserver) {

        long TimeRequest = System.currentTimeMillis() - request.getTimeRequest();

        /*Generate Cars*/
        _Repository repo = new _Repository();
        repo.GenerateCars(request.getOwnersCount(),16000);
        List<Car> cars = repo.getCars();


        /* Get cars */
        long remove = 0;
        long for_time = System.currentTimeMillis();
        Ownerships_list.Builder response = Ownerships_list.newBuilder();
        for(int i=0; i<request.getOwnersCount(); i++){
            Ownerships.Builder ownership = Ownerships.newBuilder();
            Owner o = request.getOwners(i);
            ownership.setOwner(o);
            long one = System.currentTimeMillis();
            for (Car c : cars) {
                if (c.getOwnerId() == o.getId()){
                    ownership.addCar(c);
                }
            }
            remove += System.currentTimeMillis() - one;
            response.addOwnership(ownership);
        }
        /* Send to client */
        response.setTimeRequest(TimeRequest);
        long timer = for_time + remove;
        Ownerships_list Response = response.setTimeReply(timer).build();
        responseObserver.onNext(Response);
        responseObserver.onCompleted();
    }
}
