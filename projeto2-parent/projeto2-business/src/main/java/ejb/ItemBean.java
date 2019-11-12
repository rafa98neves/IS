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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Stateless
public class ItemBean implements ItemBeanLocal {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyBay");


    public ItemBean(){

    }

    public Item findItemById(long id){
        EntityManager em = emf.createEntityManager();
        try{
            return em.find(Item.class, id);
        }catch(Exception e){
            return null;
        }finally {
            em.close();
        }
    }

    public void delete(long itemId, User u){
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        if(!et.isActive())et.begin();
        try{
            Item item = em.find(Item.class, itemId);
            u.getItems().remove(item);
            em.remove(em.contains(item) ? item : em.merge(item));
            et.commit();
        }catch(Exception e){
            et.rollback();
        }finally {
            em.close();
        }

    }

    public boolean addItem(Item item, String country, String category, User newUser){
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
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
            et.rollback();
            return false;
        }finally {
            em.close();
        }
    }

    public Item editItem(long itemId, String name, long categoryId, long countryId, String picture, float price){
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
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
            et.rollback();
            return null;
        }finally {
            em.close();
        }
    }

    public List<Item> orderItems(List<Item> items, String parameter, String order){
        Comparator<Item> comp = null;
        switch (parameter){
            case "name":{
                if(order.equals("desc")){
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
                if(order.equals("desc")){
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
                if(order.equals("desc")){
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


    public List<Item> searchAllItems(String searchString, String parameter, String order){
        EntityManager em = emf.createEntityManager();
        try{
            List<Item> items = em.createQuery("from ITEMS where name like concat('%',?1,'%') ")
                    .setParameter(1, searchString)
                    .getResultList();
            if(parameter != null) return orderItems(items, parameter, order);
            else return items;
        }catch (Exception e){
            return Collections.emptyList();
        }finally {
            em.close();
        }

    }


    public List<Item> searchItemsByCategory(String searchString, long cId, String parameter, String order){
        EntityManager em = emf.createEntityManager();
        try{
            Category category = em.find(Category.class, cId);
            List<Item> items = em.createQuery("from ITEMS where category = ?1 and name like concat('%',?2,'%')")
                    .setParameter(1, category)
                    .setParameter(2, searchString)
                    .getResultList();
            if(parameter != null) return orderItems(items, parameter, order);
            else return items;
        }catch (Exception e){
            return Collections.emptyList();
        }finally {
            em.close();
        }

    }

    public List<Item> searchItemsByPriceRange(String searchString, float minPrice, float maxPrice, String parameter, String order){
        EntityManager em = emf.createEntityManager();
        try{
            List<Item> items = em.createQuery("from ITEMS where price between ?1 and ?2 and name like concat('%',?3,'%')")
                    .setParameter(1, minPrice)
                    .setParameter(2, maxPrice)
                    .setParameter(3, searchString)
                    .getResultList();
            if(parameter != null) return orderItems(items, parameter, order);
            else return items;
        }catch (Exception e){
            return Collections.emptyList();
        }finally {
            em.close();
        }
    }


    public List<Item> searchItemsByCountry(String searchString, long cId, String parameter, String order){
        EntityManager em = emf.createEntityManager();
        try{
            Country country = em.find(Country.class, cId);
            List<Item> items = em.createQuery("from ITEMS where country = ?1 and name like concat('%',?2,'%')")
                    .setParameter(1, country)
                    .setParameter(2, searchString)
                    .getResultList();
            if(parameter != null) return orderItems(items, parameter, order);
            else return items;
        }catch (Exception e){
            return Collections.emptyList();
        }finally {
            em.close();
        }
    }


    public List<Item> searchItemsByDateOfInsertion(String searchString, Date d, String parameter, String order) {
        EntityManager em = emf.createEntityManager();
        try{
            List<Item> items = em.createQuery("from ITEMS where dateOfInsertion > ?1 and name like concat('%',?2,'%')")
                    .setParameter(1, d)
                    .setParameter(2, searchString)
                    .getResultList();
            if(parameter != null) return orderItems(items, parameter, order);
            else return items;
        }catch (Exception e){
            return Collections.emptyList();
        }finally {
            em.close();
        }

    }


}
