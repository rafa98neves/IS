package project3.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Country implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlAttribute
    int id;

    private String country;
    public Country() {}

    public Country(int id, String name) {
        this.id = id;
        this.country = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void update(String name) {
        this.country = name;
    }

}
