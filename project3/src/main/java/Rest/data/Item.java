package Rest.data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="items")
public class Item{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int item_id;
    @Column(name="item_name", length=50, nullable=false, unique=false)
    private String item_name;
    @Column(name="item_price", nullable=false, unique=false)
    private float item_price;

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public float getItem_price() {
        return item_price;
    }

    public void setItem_price(float item_price) {
        this.item_price = item_price;
    }

}
