package project3.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Sale implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlAttribute
    int id;

    private Item item;
    private float price;
    private int units;
    private Country country;

    public Sale() {}

    public Sale(int n){
    }

    public Sale(Item item, int units, Country country) {
        this.item = item;
        this.units = units;
        this.country = country;
        this.price = item.getItem_price() * units;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void update(Item item, int units, Country country) {
        this.units = units;
        this.item = item;
        this.country = country;
        this.price = item.getItem_price() * units;
    }

}
