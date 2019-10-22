package data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "USERS")
@Table(name = "USERS")
public class User implements Serializable {
    @Id
    @Column(nullable = false, length = 32)
    private String username;
    @Column(nullable = false, length = 64)
    private String password;
    @OneToOne
    private Country country;
    @Column(nullable = false)
    private Date birthdate;
    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "owner")
    private List<Item> items;

    public User(){

    }

    public User(String username, String password, Country country, Date birthdate, String email) {
        this.username = username;
        this.password = password;
        this.country = country;
        this.birthdate = birthdate;
        this.email = email;
        items = new ArrayList<Item>();
    }
}
