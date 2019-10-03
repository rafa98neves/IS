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
        OwnerListXML owners = new OwnerListXML();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance( OwnerListXML.class );
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(request.getXML());
            owners = (OwnerListXML) jaxbUnmarshaller.unmarshal(reader);
        }catch (Exception e){
            System.out.println("Error creating marshalOBJ: " + e);
        }

        /*Generate Cars*/
        _Repository repo = new _Repository();
        repo.GenerateCarsXML(owners.size()-1,10);
        OwnershipXML cars = repo.getCarsXML();

        /* Get cars */
        OwnershipXML cars_response = new OwnershipXML();
        ArrayList<CarXML> cars_list = cars.getCars();
        ArrayList<OwnerXML> owners_list = owners.getOwners();
        for(OwnerXML o: owners_list){
            for (CarXML c : cars_list){
                System.out.println(c.getOwnerId());
                if (c.getOwnerId() == o.getId()){
                    cars_response.add(c);
                }
            }

        }

        /* Send to client */
        StringWriter writer = new StringWriter();
        try {
            JAXBContext contextObj = JAXBContext.newInstance(OwnershipXML.class);
            Marshaller marshallerObj = contextObj.createMarshaller();
            marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshallerObj.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshallerObj.marshal(cars_response, writer);

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
