package ejb;

import data.Country;
import data.User;

import javax.ejb.Remote;
import java.sql.Date;

@Remote
public interface UserBeanRemote {
    void edit(String name, Country country, String email, String password, Date birthdate);
    void deleteUser(User u);
    void logout();
}
