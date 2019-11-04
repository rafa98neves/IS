package ejb;

import data.Category;
import data.Country;
import data.Item;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Date;
import java.util.List;

@Stateful
public class ItemBean implements ItemBeanLocal {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyBay");
    EntityManager em = emf.createEntityManager();


    public ItemBean(){

    }

    public void delete(Item item){
        em.getTransaction().begin();
        em.remove(item);
        em.getTransaction().commit();
    }

    public void addItem(Item item){
        em.getTransaction().begin();
        em.persist(item);
        em.getTransaction().commit();
    }

    public void editItem(Item item, String name, Category category, Country country, String picture){
        em.createQuery("UPDATE ITEMS set name = ?, category = ?, country = ?, picture = ? where id = ?")
            .setParameter(0, name)
            .setParameter(1, category)
            .setParameter(2 ,country)
            .setParameter(3, picture)
            .setParameter(4, item.getId())
            .executeUpdate();
    }

    public List<Item> findItemsByDateOfInsertion(){
        List<Item> items = em.createQuery("select Item(id, name, country, category, picture, dateOfInsertion) from ITEMS order by dateOfInsertion")
            .getResultList();
        return items;
    }

    public List<Item> searchAllItems(String searchString){
        List<Item> items = em.createQuery("select Item(id, name, price, picture, dateOfInsertion, category, country, owner) from ITEMS where name like concat('%',?,'%') ")
                .setParameter(0, searchString)
                .getResultList();
        return items;

    }

    public List<Item> searchItemsByCategory(String searchString, Category c){
        List<Item> items = em.createQuery("select Item(id, name, price, picture, dateOfInsertion, category, country, owner) from ITEMS where category = ? and name like concat('%',?,'%')")
                .setParameter(0, c)
                .setParameter(1, searchString)
                .getResultList();
        return items;
    }

    public List<Item> searchItemsByPriceRange(String searchString, float minPrice, float maxPrice){
        List<Item> items = em.createQuery("select Item(id, name, price, picture, dateOfInsertion, category, country, owner) from ITEMS where price between ? and ? and name like concat('%',?,'%')")
                .setParameter(0, minPrice)
                .setParameter(1, maxPrice)
                .setParameter(2, searchString)
                .getResultList();

        return items;
    }

    public List<Item> searchItemsByCountry(String searchString, Country c){
        List<Item> items = em.createQuery("select Item(id, name, price, picture, dateOfInsertion, category, country, owner) from ITEMS where country = ? and name like concat('%',?,'%')")
                .setParameter(0, c)
                .setParameter(1, searchString)
                .getResultList();
        return items;
    }

    public List<Item> searchItemsByDateOfInsertion(String searchString, Date d) {
        List<Item> items = em.createQuery("select Item(id, name, price, picture, dateOfInsertion, category, country, owner) from ITEMS where dateOfInsertion > ? and name like concat('%',?,'%')")
                .setParameter(0, d)
                .setParameter(1, searchString)
                .getResultList();
        return items;
    }
}
