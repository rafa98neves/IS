package org;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.grpcFA.XML;
import org.grpcFA.XMLRequestGrpc;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;


public class gRPCClientXML {
    public static void main(String[] args) throws JAXBException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8082)
                .usePlaintext()
                .build();

        XMLRequestGrpc.XMLRequestBlockingStub stub
                = XMLRequestGrpc.newBlockingStub(channel);

        JAXBContext contextObj = JAXBContext.newInstance(OwnerListXML.class);
        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshallerObj.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

        /*Generate Owners*/
        _Repository repo = new _Repository();
        repo.GenerateOwnersXML(3);
        OwnerListXML owners = repo.getOwnersXML();

        /* Send to Server*/
        StringWriter request = new StringWriter();
        marshallerObj.marshal(owners, request);

        String result = request.toString();
        XML xml = XML.newBuilder()
                .setXML(result)
                .build();

        /* Get response and shutdown */
        XML response = stub.request(xml);
        System.out.println(response);
        channel.shutdown();
    }
}