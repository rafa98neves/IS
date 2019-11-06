package ejb;

import data.Category;
import data.Country;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Stateful
public class InformationBean implements InformationBeanLocal{
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyBay");
    EntityManager em = emf.createEntityManager();

    List<Country> countries;
    List<Category> categories;

    public InformationBean(){
        this.countries = this.findAllCountries();
        this.categories = this.findAllCategories();
    }

    public List<Country> findAllCountries(){
        return em.createQuery("from COUNTRIES c order by c.name ASC").getResultList();
    }

    public List<Category> findAllCategories(){
        return em.createQuery("from CATEGORIES c order by c.type ASC").getResultList();
    }

    public List<Country> getCountries() {
        return countries;
    }

    public List<Category> getCategories() {
        return categories;
    }
}
