package ejb;

import data.Category;
import data.Country;
import data.Item;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ItemBeanLocal {
    void delete(Item item);
    void addItem(Item item);
    void editItem(Item item, String name, Category category, Country country, String picture);
    List<Item> findItemsByDateOfInsertion();
}
