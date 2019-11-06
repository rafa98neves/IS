package ejb;

import data.Country;
import data.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.Date;


@Stateless
public class RegisterBean implements RegisterBeanLocal {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyBay");
    EntityManager em = emf.createEntityManager();
    EntityTransaction et = em.getTransaction();


    public RegisterBean(){

    }

    public boolean registerUser(String name, String email, Country country, Date birthdate, String password){
        if(!et.isActive()) et.begin();
        User newUser = new User(name, password, country, birthdate, email);
        try {
            em.persist(newUser);
            et.commit();
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
