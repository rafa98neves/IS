package Classes;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;
 
@Entity
@Table
public class User implements Serializable {
    @Column
    @Id
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String country;
    @Column
    private Date birthdate;

    public User(){

    }

    public User(String username, String password, String email, String country, Date birthdate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.country = country;
        this.birthdate = birthdate;
    }



}
