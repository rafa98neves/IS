import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Owner {
    private long id;
    private String name;
    private int telephone;
    private String address;
    private List<Long> car_ids;

    public Owner(long id, String name, int telephone, String address, List<Long> car_ids) {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.address = address;
        this.car_ids = car_ids;
    }

    @XmlAttribute
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute
    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    @XmlAttribute
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @XmlAttribute
    public List<Long> getCar_ids() {
        return car_ids;
    }

    public void setCar_ids(List<Long> car_ids) {
        this.car_ids = car_ids;
    }
}
