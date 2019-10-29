package ejb;

import data.Country;

import javax.ejb.Remote;
import java.sql.Date;

@Remote
public interface RegisterBeanRemote {
    void registerUser(String username, String email, Country country, Date birthdate, String password);
}
