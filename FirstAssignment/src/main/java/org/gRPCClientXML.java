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

        /* FAZER REPOSITORIO */
        List<Long> ids1 = new ArrayList<Long>();
        ids1.add(1l);
        ids1.add(2l);

        List<Long> ids2 = new ArrayList<Long>();
        ids2.add(3l);
        ids2.add(4l);

        OwnerXML owner1 = new OwnerXML(1,"Rafael",96988888, "Rua Santa Rita",ids1);
        OwnerXML owner2 = new OwnerXML(2,"Joao",96988888, "Rua Santa Rita",ids2);
        OwnerListXML list = new OwnerListXML();
        list.add(owner1);
        list.add(owner2);

        /* ------------- */

        StringWriter request = new StringWriter();
        marshallerObj.marshal(list, request);

        String result = request.toString();
        XML xml = XML.newBuilder()
                .setXML(result)
                .build();

        XML response = stub.request(xml);
        System.out.println(response);
        channel.shutdown();
    }
}