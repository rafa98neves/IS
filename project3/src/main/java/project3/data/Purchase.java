package project3.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Purchase implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlAttribute
    int id;

    private Item item;
    private int units;
    private float price;

    public Purchase() {}

    public Purchase(Item item, int units) {
        this.item = item;
        this.units = units;
        this.price = item.getPrice() * units;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void update(Item item, int units) {
        this.units = units;
        this.item = item;
        this.price = item.getPrice() * units;
    }
}
