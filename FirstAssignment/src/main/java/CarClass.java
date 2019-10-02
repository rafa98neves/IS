import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CarClass {
    private long id;
    private long owner_id;
    private String brand;
    private String model;
    private int engineSize;
    private int power;
    private float consumption;
    private String plate;

    public CarClass() {}
    public CarClass(long id, String brand,String model, int engineSize, int power, float consumption, String plate) {
        super();
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.engineSize  = engineSize;
        this.power = power;
        this.consumption = consumption;
        this.plate = plate;
    }
    @XmlAttribute
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    @XmlElement
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    @XmlElement
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    @XmlElement
    public int getEngineSize() {
        return engineSize;
    }
    public void setEngineSize(int engineSize) {
        this.engineSize = engineSize;
    }
    @XmlElement
    public int getPower() {
        return power;
    }
    public void setPower(int power) {
        this.power = power;
    }
    @XmlElement
    public float getConsumption() {
        return consumption;
    }
    public void setConsumption(float consumption) {
        this.consumption = consumption;
    }
    @XmlElement
    public String getPlate() {
        return plate;
    }
    public void setPlate(String plate) {
        this.plate = plate;
    }
    @XmlElement
    public void setOwner_id(long owner_id) {
        this.owner_id = owner_id;
    }
}