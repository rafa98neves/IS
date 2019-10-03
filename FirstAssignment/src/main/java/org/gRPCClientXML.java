package org;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.grpcFA.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.List;


public class gRPCClientXML {
    public static void main(String[] args) throws JAXBException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8082)
                .usePlaintext()
                .build();

        XMLRequestGrpc.XMLRequestBlockingStub stub
                = XMLRequestGrpc.newBlockingStub(channel);

        JAXBContext contextObj = JAXBContext.newInstance(Class.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        Repository repo = new Repository();
        List<Owner> owners = repo.getOwners();

        Marshaller m = contextObj.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter request = new StringWriter();
        m.marshal(owners, request);

        String result = request.toString();

        System.out.println(result);

        XML xml = XML.newBuilder()
                .setXML(result)
                .build();

        XML response = stub.request(xml);

        System.out.println(response);

        channel.shutdown();
    }
}