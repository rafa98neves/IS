package org;

import java.util.List;
import io.grpc.stub.StreamObserver;
import org.grpcFA.*;

public class _RequestServiceImpl extends OwnerRequestGrpc.OwnerRequestImplBase {

    @Override
    public void request(OwnersRequest request, StreamObserver<Ownerships_list> responseObserver) {

        /*Generate Cars*/
        _Repository repo = new _Repository();
        repo.GenerateCars(request.getOwnersCount(),10);
        List<Car> cars = repo.getCars();


        /* Get cars */
        Ownerships_list.Builder response = Ownerships_list.newBuilder();
        for(int i=0; i<request.getOwnersCount(); i++){
            Ownerships.Builder ownership = Ownerships.newBuilder();
            Owner o = request.getOwners(i);
            ownership.setOwner(o);
            for (Car c : cars) {
                System.out.println(c.getId());
                if (c.getOwnerId() == o.getId()){
                    ownership.addCar(c);
                }
            }
            response.addOwnership(ownership);
        }

        /* Send to client */
        Ownerships_list Response = response.build();
        responseObserver.onNext(Response);
        responseObserver.onCompleted();
    }
}
