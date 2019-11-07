package ejb;

import data.Category;
import data.Country;

import java.util.List;

public interface InformationBeanLocal {
    List<Country> findAllCountries();
    List<Category> findAllCategories();
    List<Country> getCountries();
    List<Category> getCategories();
}
