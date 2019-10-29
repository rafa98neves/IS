package data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity(name = "ITEMS")
@Table(name = "ITEMS")
public class Item implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false, length=64)
    private String name;
    @Column(nullable = false, length = 128)
    private String picture;
    @Column(nullable = false)
    private float price;
    @Column(nullable = false)
    private Date dateOfInsertion;
    @ManyToOne
    private Category category;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getDateOfInsertion() {
        return dateOfInsertion;
    }

    public void setDateOfInsertion(Date dateOfInsertion) {
        this.dateOfInsertion = dateOfInsertion;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @OneToOne
    private Country country;
    @ManyToOne
    private User owner;

    public Item(){

    }

    public Item(String name, String picture, float price, Date dateOfInsertion, Category category, Country country, User owner) {
        this.name = name;
        this.picture = picture;
        this.price = price;
        this.dateOfInsertion = dateOfInsertion;
        this.category = category;
        this.country = country;
        this.owner = owner;
    }
}
