package dao;

import entities.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by admin on 8/28/17.
 */
public interface UserDao extends DAO<User> {
    List<User> getUserById (long id) throws SQLException;
    List<User> getAllUsers() throws SQLException;
    public boolean checkUser(String email, String password) throws SQLException;
}
