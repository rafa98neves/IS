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

        JAXBContext contextObj = JAXBContext.newInstance(OwnerXML.class);
        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshallerObj.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

        _Repository repo = new _Repository();

        List<Long> ids = new ArrayList<Long>();
        ids.add(1l);
        ids.add(2l);
        OwnerXML owner1 = new OwnerXML(1,"Rafael",96988888, "Rua Santa Rita",ids);

        StringWriter request = new StringWriter();
        marshallerObj.marshal(owner1, request);

        String result = request.toString();
        XML xml = XML.newBuilder()
                .setXML(result)
                .build();

        XML response = stub.request(xml);

        channel.shutdown();
    }
}