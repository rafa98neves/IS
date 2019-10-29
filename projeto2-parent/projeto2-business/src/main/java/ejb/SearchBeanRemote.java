package ejb;

import data.Category;
import data.Country;
import data.Item;

import javax.ejb.Remote;
import java.sql.Date;
import java.util.List;

@Remote
public interface SearchBeanRemote {
    List<Item> searchAllItems(String searchString);
    List<Item> searchItemsByCategory(String searchString, Category c);
    List<Item> searchItemsByPriceRange(String searchString, float minPrice, float maxPrice);
    List<Item> searchItemsByCountry(String searchString, Country c);
    List<Item> searchItemsByDateOfInsertion(String searchString, Date d);
}
