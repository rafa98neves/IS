package ejb;

import data.Country;

import javax.ejb.Local;
import java.sql.Date;
import java.util.List;

@Local
public interface RegisterBeanLocal {
    boolean registerUser(String username, String email, String country, Date birthdate, String password);
}
