package data;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "COUNTRIES")
@Table(name = "COUNTRIES")
public class Country implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false, length = 32)
    private String name;

    public Country(){

    }

    public Country(String name) {
        this.name = name;
    }
}
