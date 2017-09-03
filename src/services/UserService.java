package services;

import entities.User;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by admin on 8/28/17.
 */
public interface UserService {
    User save(User user);

    User get(Serializable id);

    void update(User user);

    int delete(Serializable id);

    List<User> getUserById (long id);

    List<User> getUsers() throws SQLException;
}
