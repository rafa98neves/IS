package ejb;

import data.Country;

import javax.ejb.Local;
import java.sql.Date;

@Local
public interface RegisterBeanLocal {
    boolean registerUser(String username, String email, Country country, Date birthdate, String password);
}
