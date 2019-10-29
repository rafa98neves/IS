package ejb;
import data.User;

import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless
public class LoginBean implements LoginBeanLocal {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyBay");
    EntityManager em = emf.createEntityManager();

    public LoginBean(){

    }

    public User login(String email, String password){
        return (User) em.createQuery("SELECT User(email, name, country, birthdate)from USERS where email = ? and password = ? ")
            .setParameter(0, email)
            .setParameter(1, password)
            .getSingleResult();

    }
}

