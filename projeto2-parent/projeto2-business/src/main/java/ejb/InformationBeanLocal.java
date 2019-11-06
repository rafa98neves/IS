package ejb;

import data.Category;
import data.Country;

import javax.ejb.Local;
import java.util.List;

@Local
public interface InformationBeanLocal {
    List<Country> findAllCountries();
    List<Category> findAllCategories();
    List<Country> getCountries();
    List<Category> getCategories();
}
