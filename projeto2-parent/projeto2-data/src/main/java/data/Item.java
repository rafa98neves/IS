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
    @Column(nullable = false, length = 128)
    private String picture;
    @Column(nullable = false)
    private float price;
    @Column(nullable = false)
    private Date dateOfInsertion;
    @ManyToOne
    private Category category;
    @OneToOne
    private Country country;
    @ManyToOne
    private User owner;

    public Item(){

    }

    public Item(Country country, String picture, Category category, User owner) {
        this.country = country;
        this.picture = picture;
        this.category = category;
        this.owner = owner;
    }
}
