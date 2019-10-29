package ejb;

import data.Category;
import data.Country;
import data.Item;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ItemBeanRemote {
    void delete(Item item);
    void addItem(Item item);
    void editItem(Item item, String name, Category category, Country country, String picture);
    List<Item> findItemsByDateOfInsertion();
}
