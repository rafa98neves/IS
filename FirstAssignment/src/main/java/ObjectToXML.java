import java.io.FileOutputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;


public class ObjectToXML {

    public String asString(Object pObject) throws Exception{

        StringWriter sw = new StringWriter();

        JAXBContext contextObj = JAXBContext.newInstance(Class.class);
        Marshaller marshallerObj = contextObj.createMarshaller();

        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshallerObj.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

        marshallerObj.marshal(pObject, sw);
        return sw.toString();
    }
}  