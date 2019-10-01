package org;

import io.grpc.stub.StreamObserver;
import org.grpcFA.*;

public class HelloServiceImpl extends OwnerRequestGrpc.OwnerRequestImplBase {

    @Override
    public void hello(
        HelloRequest request, StreamObserver<HelloReply> responseObserver) {

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
