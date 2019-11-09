package ejb;

import data.Category;
import data.Country;
import data.Item;
import data.User;

import javax.ejb.Local;
import java.sql.Date;
import java.util.List;

@Local
public interface ItemBeanLocal {
    void delete(long itemId, User u);
    boolean addItem(Item item, String country, String category, User newUser);
    Item editItem(long itemId, String name, long categoryId, long countryId, String picture, float price);
    Item findItemById(long id);
    List<Item> orderItems(List<Item> items, String parameter, String order);
    List<Item> searchAllItems(String searchString);
    List<Item> searchItemsByCategory(String searchString, String cId);
    List<Item> searchItemsByPriceRange(String searchString, String minPrice, String maxPrice);
    List<Item> searchItemsByCountry(String searchString, String cId);
    List<Item> searchItemsByDateOfInsertion(String searchString, String d);
}
