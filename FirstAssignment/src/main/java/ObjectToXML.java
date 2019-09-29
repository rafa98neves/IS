import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;


public class ObjectToXML {

    public static void main(String[] args) throws Exception{
        JAXBContext contextObj = JAXBContext.newInstance(Class.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        //Car Car1=new Car(201134441110l,"Alberto",20);
        //marshallerObj.marshal(Car, new FileOutputStream("cars.xml"));

    }
}  