import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Car {
    private long id;
    private String brand;
    private String model;
    private int engineSize;
    private int power;
    private float consumption;
    private String plate;
    private long ownerId;

    public Car(long id, String brand, String model, int engineSize, int power, float consumption, String plate, long ownerId){
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.engineSize = engineSize;
        this.power = power;
        this.consumption = consumption;
        this.plate = plate;
        this.ownerId = ownerId;
    }

    @XmlAttribute
    public String getBrand(){
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @XmlAttribute
    public long getId(){
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @XmlAttribute
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @XmlAttribute
    public int getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(int engineSize) {
        this.engineSize = engineSize;
    }

    @XmlAttribute
    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @XmlAttribute
    public float getConsumption() {
        return consumption;
    }

    public void setConsumption(float consumption) {
        this.consumption = consumption;
    }

    @XmlAttribute
    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    @XmlAttribute
    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }
}
