package ejb;

import data.Category;
import data.Country;
import data.Item;

import javax.ejb.Local;
import java.sql.Date;
import java.util.List;

@Local
public interface ItemBeanLocal {
    void delete(Item item);
    void addItem(Item item);
    void editItem(Item item, String name, Category category, Country country, String picture);
    List<Item> findItemsByDateOfInsertion();
    List<Item> searchAllItems(String searchString);
    List<Item> searchItemsByCategory(String searchString, Category c);
    List<Item> searchItemsByPriceRange(String searchString, float minPrice, float maxPrice);
    List<Item> searchItemsByCountry(String searchString, Country c);
    List<Item> searchItemsByDateOfInsertion(String searchString, Date d);
}