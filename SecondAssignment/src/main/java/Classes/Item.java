package Classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
public class Item implements Serializable {
    @Column
    @Id
    private int id;
    @Column
    private String name;
    @Column
    private String category;
    @Column
    private String country;
    @Column
    private String photograph;


}
