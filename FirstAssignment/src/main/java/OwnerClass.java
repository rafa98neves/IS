import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;


@XmlRootElement
public class OwnerClass{
    private long id;
    private String name;
    private int telephone;
    private String address;
    private ArrayList ownerships;

    public OwnerClass(){}
    public OwnerClass(long id, String name, int telephone, String address){
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.address = address;
    }
    public void addCar(CarClass car){
        ownerships.add(car.getId());
        car.setOwner_id(this.id);
    }
    @XmlAttribute
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    @XmlElement
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @XmlElement
    public int getTelephone() {
        return telephone;
    }
    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }
    @XmlElement
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
