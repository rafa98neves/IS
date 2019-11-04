package ejb;

import data.Country;
import data.User;

import javax.ejb.Local;
import java.sql.Date;

@Local
public interface UserBeanLocal {
    void edit(String name, Country country, String email, String password, Date birthdate);
    void deleteUser(User u);
}
