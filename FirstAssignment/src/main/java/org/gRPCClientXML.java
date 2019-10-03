package org;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.grpcFA.XML;
import org.grpcFA.XMLRequestGrpc;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class gRPCClientXML {
    public static void main(String[] args) throws JAXBException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8082)
                .usePlaintext()
                .build();

        XMLRequestGrpc.XMLRequestBlockingStub stub
                = XMLRequestGrpc.newBlockingStub(channel);

        /*Generate Owners*/
        _Repository repo = new _Repository();
        repo.GenerateOwnersXML(25000);
        OwnerListXML owners = repo.getOwnersXML();

        /* Send to Server*/
        long start = System.currentTimeMillis();
        JAXBContext contextObj = JAXBContext.newInstance(OwnerListXML.class);
        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshallerObj.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

        StringWriter request = new StringWriter();
        marshallerObj.marshal(owners, request);

        String result = request.toString();
        XML xml = XML.newBuilder()
                .setXML(result)
                .setTimeRequest(start)
                .build();

        /* Get response and shutdown */
        XML response = stub.request(xml);

        StringReader reader = new StringReader(response.getXML());
        contextObj = JAXBContext.newInstance(OwnershipXML.class);
        Unmarshaller jaxbUnmarshaller = contextObj.createUnmarshaller();
        OwnershipXML cars = (OwnershipXML) jaxbUnmarshaller.unmarshal(reader);

        long timer = System.currentTimeMillis() - response.getTimeReply() + response.getTimeRequest();
        System.out.println(timer);

        try{
            channel.awaitTermination(5, TimeUnit.SECONDS);
        }catch(Exception e){
            channel.shutdownNow();
        }
    }
}