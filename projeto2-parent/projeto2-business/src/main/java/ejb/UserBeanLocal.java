package ejb;

import data.User;

import javax.ejb.Local;
import java.sql.Date;

@Local
public interface UserBeanLocal {
    User edit(String name, Long country_id, String email, Date birthdate);
    void deleteUser(User u);
}
