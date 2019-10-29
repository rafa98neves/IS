package ejb;

import data.User;

import javax.ejb.Remote;

@Remote
public interface LoginBeanRemote {
    User login(String email, String password);
}
