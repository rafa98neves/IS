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
        try{
            User u = (User) em.createQuery("from USERS u where u.email = :email and u.password = :psw")
                    .setParameter("email", email)
                    .setParameter("psw", password)
                    .getSingleResult();
            return u;
        }catch(NoResultException nre){
            return null;
        }
    }
}

