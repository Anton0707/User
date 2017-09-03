package services.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entities.User;
import services.ServiceException;
import services.UserService;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by admin on 8/28/17.
 */
public class UserServiceImpl extends AbstractService implements UserService {

    private static volatile UserService INSTANCE = null;

    private UserDao userDao = UserDaoImpl.getInstance();

    private UserServiceImpl() {
    }

    @Override
    public User save(User user) {
        try {
            user = userDao.save(user);
        } catch (SQLException e) {
            throw new ServiceException("Error creating User" + user);
        }
        return user;
    }

    @Override
    public User get(Serializable id) {
        try {
            return userDao.get(id);
        } catch (SQLException e) {
            throw new ServiceException("Error geting User by id " + id);
        }
    }

    @Override
    public void update(User user) {
        try {
            userDao.update(user);
        } catch (SQLException e) {
            throw new ServiceException("Error updating User" + user);
        }
    }

    @Override
    public int delete(Serializable id) {
        return 0;
    }

    @Override
    public List<User> getUserById(long id) {
        return null;
    }

    public List<User> getUsers() throws SQLException {
        return userDao.getAllUsers();
    }

    public static UserService getInstance() {
        UserService userService = INSTANCE;
        if (userService == null) {
            synchronized (UserDaoImpl.class) {
                userService = INSTANCE;
                if (userService == null) {
                    INSTANCE = userService = new UserServiceImpl();
                }
            }
        }

        return userService;
    }
}
