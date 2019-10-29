package ejb;

import data.User;

import javax.ejb.Local;

@Local
public interface LoginBeanLocal {
    User login(String email, String password);
}
