package org;

import com.sun.jmx.mbeanserver.Repository;
import io.grpc.stub.StreamObserver;
import org.grpcFA.HelloReply;
import org.grpcFA.HelloRequest;
import org.grpcFA.OwnerRequestGrpc;

public class RequestServiceImpl extends OwnerRequestGrpc.OwnerRequestImplBase {

    @Override
    public void request(
            OwnerRequest request, StreamObserver<Cars> responseObserver) {

        String greeting = new StringBuilder()
                .append("Hello, ")
                .append(request.getName())
                .toString();

        HelloReply response = HelloReply.newBuilder()
                .setMessage(greeting)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
