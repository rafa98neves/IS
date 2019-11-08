package data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "USERS")
@Table(name = "USERS")
public class User implements Serializable {
    @Column(nullable = false, length = 32)
    private String name;
    @Column(nullable = false, length = 64)
    private String password;
    @OneToOne()
    private Country country;
    @Column(nullable = false)
    private Date birthdate;
    @Id
    @Column(nullable = false, length=48)
    private String email;
    @OneToMany(mappedBy = "owner")
    private List<Item> items;

    public User(){
        this.items = new ArrayList<>();
    }

    public User(String name, String password, Country country, Date birthdate, String email) {
        this.name = name;
        this.password = password;
        this.country = country;
        this.birthdate = birthdate;
        this.email = email;
        this.items = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items){this.items = items;}

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
