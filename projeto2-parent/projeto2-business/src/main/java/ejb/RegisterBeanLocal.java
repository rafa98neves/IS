package ejb;

import javax.ejb.Local;
import java.sql.Date;

@Local
public interface RegisterBeanLocal {
    boolean registerUser(String username, String email, String country, Date birthdate, String password);
}
