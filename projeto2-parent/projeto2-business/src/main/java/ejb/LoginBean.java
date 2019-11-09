package ejb;
import data.Item;
import data.User;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.Comparator;

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
            u.getItems().sort(new Comparator<Item>() {
                @Override
                public int compare(Item o1, Item o2) {
                    return o1.getDateOfInsertion().compareTo(o2.getDateOfInsertion());
                }
            });

            return u;
        }catch(NoResultException nre){
            return null;
        }
    }
}

