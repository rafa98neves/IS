import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Car {
    private long id;
    private long owner_id;
    private String brand;
    private String model;
    private float engineSize;
    private float power;
    private int consumption;
    private String plate;

    public Car() {}
    public Car(long id, String brand,String model, float engineSize, float power, int consumption, String plate) {
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
    public float getEngineSize() {
        return engineSize;
    }
    public void setEngineSize(float engineSize) {
        this.engineSize = engineSize;
    }
    @XmlElement
    public float getPower() {
        return power;
    }
    public void setPower(float power) {
        this.power = power;
    }
    @XmlElement
    public int getConsumption() {
        return consumption;
    }
    public void setConsumption(int consumption) {
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