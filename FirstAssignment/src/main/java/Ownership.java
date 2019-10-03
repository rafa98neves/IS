import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;

@XmlRootElement
@XmlSeeAlso(Car.class)
public class Ownership extends ArrayList<Car> {

    @XmlElement()
    public ArrayList<Car> getCars(){return this; }

}
