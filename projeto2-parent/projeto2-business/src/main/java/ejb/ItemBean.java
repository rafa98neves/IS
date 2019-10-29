package ejb;

import data.Category;
import data.Country;
import data.Item;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Stateful
public class ItemBean implements ItemBeanLocal {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyBay");
    EntityManager em = emf.createEntityManager();


    public ItemBean(){

    }

    public void delete(Item item){
        em.remove(item);
    }

    public void addItem(Item item){
        em.persist(item);
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
}
