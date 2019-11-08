package ejb;

import data.Category;
import data.Country;
import data.Item;
import data.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ItemBean implements ItemBeanLocal {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyBay");
    EntityManager em = emf.createEntityManager();
    EntityTransaction et = em.getTransaction();


    public ItemBean(){

    }

    public void delete(Item item){
        if(!et.isActive())et.begin();
        em.remove(item);
        et.commit();
    }

    public boolean addItem(Item item, String country, String category, User newUser){
        if(!et.isActive())et.begin();
        try{
            Country count = em.find(Country.class, Long.parseLong(country));
            item.setCountry(count);
            Category cat = em.find(Category.class, Long.parseLong(category));
            item.setCategory(cat);
            newUser.getItems().add(item);
            em.merge(newUser);
            em.persist(item);
            et.commit();
            return true;
        } catch(Exception e){
            return false;
        }
    }

    public Item editItem(long itemId, String name, Category category, Country country, String picture){
        em.createQuery("UPDATE ITEMS set name = ?1, category = ?2, country = ?3, picture = ?4 where id = ?5")
            .setParameter(1, name)
            .setParameter(2, category)
            .setParameter(3 ,country)
            .setParameter(4, picture)
            .setParameter(5, itemId)
            .executeUpdate();
        Item item = (Item) em.createQuery("from ITEMS where id = ?1")
                .setParameter(1, itemId)
                .getSingleResult();
        return item;
    }

    public List<Item> findItemsByDateOfInsertion(){
        List<Item> items = em.createQuery("from ITEMS order by dateOfInsertion")
            .getResultList();
        return items;
    }

    public List<Item> searchAllItems(String searchString){
        List<Item> items = em.createQuery("from ITEMS where name like concat('%',?1,'%') ")
                .setParameter(1, searchString)
                .getResultList();
        return items;

    }

    public List<Item> searchItemsByCategory(String searchString, Category c){
        List<Item> items = em.createQuery("from ITEMS where category = ?1 and name like concat('%',?2,'%')")
                .setParameter(1, c)
                .setParameter(2, searchString)
                .getResultList();
        return items;
    }

    public List<Item> searchItemsByPriceRange(String searchString, float minPrice, float maxPrice){
        List<Item> items = em.createQuery("from ITEMS where price between ?1 and ?2 and name like concat('%',?3,'%')")
                .setParameter(1, minPrice)
                .setParameter(2, maxPrice)
                .setParameter(3, searchString)
                .getResultList();
        return items;
    }

    public List<Item> searchItemsByCountry(String searchString, Country c){
        List<Item> items = em.createQuery("from ITEMS where country = ?1 and name like concat('%',?2,'%')")
                .setParameter(1, c)
                .setParameter(2, searchString)
                .getResultList();
        return items;
    }

    public List<Item> searchItemsByDateOfInsertion(String searchString, Date d) {
        List<Item> items = em.createQuery("from ITEMS where dateOfInsertion > ?1 and name like concat('%',?2,'%')")
                .setParameter(1, d)
                .setParameter(2, searchString)
                .getResultList();
        return items;
    }
}
