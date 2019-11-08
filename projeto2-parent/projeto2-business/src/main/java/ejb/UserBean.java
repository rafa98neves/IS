package ejb;

import data.Country;
import data.Item;
import data.User;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.Date;

@Stateful
public class UserBean implements UserBeanLocal {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyBay");
    EntityManager em = emf.createEntityManager();
    EntityTransaction et = em.getTransaction();


    public UserBean() {

    }

    public User edit(String name, Long country_id, String email, Date birthdate) {
        if(!et.isActive())et.begin();
        try {
            Country c = em.find(Country.class, country_id);
            User u = em.find(User.class, email);
            u.setEmail(email);
            u.setCountry(c);
            u.setName(name);
            u.setBirthdate(birthdate);
            em.merge(u);
            et.commit();
            return u;
        } catch (Exception e){
            return null;
        }
    }

    public void deleteUser(User u) {
        if(!em.getTransaction().isActive())em.getTransaction().begin();
        for (Item i : u.getItems()) {
            em.remove(em.contains(i) ? i : em.merge(i));
        }
        em.remove(em.contains(u) ? u : em.merge(u));
        em.getTransaction().commit();
    }

}
