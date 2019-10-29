package ejb;

import data.Country;
import data.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Date;


@Stateless
public class RegisterBean implements RegisterBeanLocal {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyBay");
    EntityManager em = emf.createEntityManager();


    public RegisterBean(){

    }

    public void registerUser(String name, String email, Country country, Date birthdate, String password){
        em.getTransaction().begin();
        User newUser = new User(name, password, country, birthdate, email);
        em.persist(newUser);
        em.getTransaction().commit();

    }


}
