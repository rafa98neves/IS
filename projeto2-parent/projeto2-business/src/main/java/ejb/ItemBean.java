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
import java.sql.Date;
import java.util.Comparator;
import java.util.List;

@Stateless
public class ItemBean implements ItemBeanLocal {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyBay");
    EntityManager em = emf.createEntityManager();
    EntityTransaction et = em.getTransaction();


    public ItemBean(){

    }

    public Item findItemById(long id){
        try{
            return em.find(Item.class, id);
        }catch(Exception e){
            return null;
        }

    }

    public void delete(long itemId, User u){
        if(!et.isActive())et.begin();
        try{
            Item item = em.find(Item.class, itemId);
            u.getItems().remove(item);
            em.remove(em.contains(item) ? item : em.merge(item));
            et.commit();
        }catch(Exception e){

        }

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

    public Item editItem(long itemId, String name, long categoryId, long countryId, String picture, float price){
        if(!et.isActive())et.begin();
        try {
            Item item = em.find(Item.class, itemId);
            Category category = em.find(Category.class, categoryId);
            Country country = em.find(Country.class, countryId);
            item.setName(name);
            item.setCategory(category);
            item.setCountry(country);
            if(picture !=null) item.setPicture(picture);
            item.setPrice(price);
            em.merge(item);
            et.commit();
            return item;
        }catch (Exception e){
            return null;
        }
    }

    public List<Item> orderItems(List<Item> items, String parameter, String order){
        Comparator<Item> comp = null;
        switch (parameter){
            case "name":{
                if(order.equals("asc")){
                    comp = new Comparator<Item>() {
                        @Override
                        public int compare(Item o1, Item o2) {
                            return o1.getName().compareTo(o2.getName());
                        }
                    };
                }else{
                    comp = new Comparator<Item>() {
                        @Override
                        public int compare(Item o1, Item o2) {
                            return o2.getName().compareTo(o1.getName());
                        }
                    };
                }
                break;
            }case "price":{
                if(order.equals("asc")){
                    comp = new Comparator<Item>() {
                        @Override
                        public int compare(Item o1, Item o2) {
                            return Float.compare(o1.getPrice(),o2.getPrice());
                        }
                    };
                }else{
                    comp = new Comparator<Item>() {
                        @Override
                        public int compare(Item o1, Item o2) {
                            return Float.compare(o2.getPrice(),o1.getPrice());
                        }
                    };
                }
                break;
            }case "dateOfInsertion":{
                if(order.equals("asc")){
                    comp = new Comparator<Item>() {
                        @Override
                        public int compare(Item o1, Item o2) {
                            return o1.getDateOfInsertion().compareTo(o2.getDateOfInsertion());
                        }
                    };
                }else{
                    comp = new Comparator<Item>() {
                        @Override
                        public int compare(Item o1, Item o2) {
                            return o2.getDateOfInsertion().compareTo(o1.getDateOfInsertion());
                        }
                    };
                }
                break;
            }
        }
        items.sort(comp);
        return items;
    }


    public List<Item> searchAllItems(String searchString){
        List<Item> items = em.createQuery("from ITEMS where name like concat('%',?1,'%') ")
                .setParameter(1, searchString)
                .getResultList();
        return items;

    }

    public List<Item> searchItemsByCategory(String searchString, String cId){
        List<Item> items = em.createQuery("from ITEMS where category = ?1 and name like concat('%',?2,'%')")
                .setParameter(1, cId)
                .setParameter(2, searchString)
                .getResultList();
        return items;
    }

    public List<Item> searchItemsByPriceRange(String searchString, String minPrice, String maxPrice){
        List<Item> items = em.createQuery("from ITEMS where price between ?1 and ?2 and name like concat('%',?3,'%')")
                .setParameter(1, minPrice)
                .setParameter(2, maxPrice)
                .setParameter(3, searchString)
                .getResultList();
        return items;
    }

    public List<Item> searchItemsByCountry(String searchString, String cId){
        List<Item> items = em.createQuery("from ITEMS where country = ?1 and name like concat('%',?2,'%')")
                .setParameter(1, cId)
                .setParameter(2, searchString)
                .getResultList();
        return items;
    }

    public List<Item> searchItemsByDateOfInsertion(String searchString, String d) {
        List<Item> items = em.createQuery("from ITEMS where dateOfInsertion > ?1 and name like concat('%',?2,'%')")
                .setParameter(1, d)
                .setParameter(2, searchString)
                .getResultList();
        return items;
    }
}
