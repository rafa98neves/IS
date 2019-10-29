package ejb;

import data.Country;
import data.Item;
import data.User;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Date;

@Stateful
public class UserBean implements UserBeanLocal {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyBay");
    EntityManager em = emf.createEntityManager();


    public UserBean(){

    }

    public void edit(String name, Country country, String email, String password, Date birthdate){
        em.createQuery("UPDATE USERS set name = ?, country = ?, password = ?, birthdate = ? where email = ?")
            .setParameter(0, name)
            .setParameter(1, country)
            .setParameter(2, password)
            .setParameter(3, birthdate)
            .setParameter(4, email)
            .executeUpdate();
    }

    public void deleteUser(User u){
        for(Item i : u.getItems()){
            em.remove(i);
        }
        em.remove(u);
    }

    public void logout(){

    }
}
