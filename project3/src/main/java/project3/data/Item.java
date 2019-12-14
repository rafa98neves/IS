package project3.data;

import java.io.Serializable;

public class Item implements Serializable {
    private static final long serialVersionUID = 1L;

    private int item_id;
    private String item_name;
    private float item_price;


    public Item() {}

    public Item(int item_id, String item_name, float item_price) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_price = item_price;
    }

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
