package ejb;

import data.Country;
import data.User;

import javax.ejb.Local;
import java.sql.Date;

@Local
public interface UserBeanLocal {
    User edit(String name, Country country, String email, Date birthdate);
    void deleteUser(User u);
}
