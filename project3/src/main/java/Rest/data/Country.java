package Rest.data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="countries")
public class Country {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int country_id;

    @Column(name="country_name", length=50, nullable=false, unique=true)
    private String country_name;

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }
}
