package org;

import java.util.List;
import io.grpc.stub.StreamObserver;
import org.grpcFA.*;

public class _RequestServiceImpl extends OwnerRequestGrpc.OwnerRequestImplBase {

    @Override
    public void request(OwnersRequest request, StreamObserver<Ownerships_list> responseObserver) {
        _Repository repo = new _Repository();
        List<Car> cars = repo.getCars();
        Ownerships_list.Builder response = Ownerships_list.newBuilder();

        for(int i=0; i<request.getOwnersCount(); i++){
            Ownerships.Builder ownership = Ownerships.newBuilder();
            Owner o = request.getOwners(i);
            ownership.setOwner(o);
            for(long l : o.getCarIdList()){
                for (Car c : cars) {
                    if (c.getId() == l){
                        ownership.addCar(c);
                    }
                }
            }
            response.addOwnership(ownership);
        }

        Ownerships_list Response = response.build();
        responseObserver.onNext(Response);
        responseObserver.onCompleted();
    }
}
