package data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity(name = "CATEGORIES")
@Table(name = "CATEGORIES")
public class Category implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(length = 32)
    private String type;
}
