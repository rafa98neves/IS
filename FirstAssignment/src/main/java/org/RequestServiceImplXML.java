package org;

import io.grpc.stub.StreamObserver;
import org.grpcFA.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class RequestServiceImplXML extends XMLRequestGrpc.XMLRequestImplBase {

    @Override
    public void request(XML request, StreamObserver<XML> responseObserver) {
        StringWriter sw = new StringWriter();
        OwnerListXML owners = new OwnerListXML();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance( OwnerListXML.class );
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(request.getXML());
            owners = (OwnerListXML) jaxbUnmarshaller.unmarshal(reader);
        }catch (Exception e){
            System.out.println("Error creating marshalOBJ: " + e);
        }

        /* FAZER REPOSITORIO */
        CarXML car1 = new CarXML(1,"Opel","Zafira", 1200,75,7.5f,"00-01-AA",1);
        CarXML car2 = new CarXML(2,"Opel","Corsa", 1200,75,7.5f,"00-02-AA",1);
        CarXML car3 = new CarXML(3,"Audi","A5", 1200,75,7.5f,"00-03-AA",2);
        CarXML car4 = new CarXML(4,"Audi","A4", 1200,75,7.5f,"00-04-AA",2);

        OwnershipXML list = new OwnershipXML();
        list.add(car1);
        list.add(car2);
        list.add(car3);
        list.add(car4);
        /* ------------- */


        OwnershipXML cars = new OwnershipXML();
        for(OwnerXML o: owners.getOwners()){
            for(long l : o.getCar_ids()){
                for (CarXML c : list) {
                    if (c.getId() == l){
                        cars.add(c);
                    }
                }
            }
        }

        StringWriter writer = new StringWriter();
        try {
            JAXBContext contextObj = JAXBContext.newInstance(OwnershipXML.class);
            Marshaller marshallerObj = contextObj.createMarshaller();
            marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshallerObj.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshallerObj.marshal(cars, writer);

        }catch (Exception e){
            System.out.println("Error creating marshalOBJ: " + e);
        }

        String response = writer.toString();
        XML xml = XML.newBuilder()
                .setXML(response)
                .build();

        XML Response = xml;
        responseObserver.onNext(Response);
        responseObserver.onCompleted();
    }
}
