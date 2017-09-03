package dao.impl;

import dao.UserDao;
import entities.User;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 8/28/17.
 */
public class UserDaoImpl extends AbstractDao implements UserDao {

    private static volatile UserDao INSTANCE = null;

    private static final String saveQuery = "INSERT INTO `USER` (LAST_NAME, FIRST_NAME, PASSWORD, LOGIN, ROLE, EMAIL, DATE_OF_BIRTH) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String updateQuery = "UPDATE `USER` SET LAST_NAME=?, FIRST_NAME=?, PASSWORD=?, LOGIN=?, ROLE=?, EMAIL=?, DATE_OF_BIRTH=? WHERE ID=?";
    private static final String getQuery = "SELECT * FROM `USER` WHERE ID=?";
    private static final String deleteQuery = "DELETE FROM `USER` WHERE ID=?";
    private static final String getUserByIdQuery = "SELECT * FROM `USER` WHERE ID=?";
    private static final String getUsers = "SELECT * FROM `USER`";
    private static final String checkUser = "SELECT * FROM `USER` WHERE EMAIL=? and PASSWORD=?";

    private PreparedStatement psSave;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psGetAll;
    private PreparedStatement psDelete;
    private PreparedStatement psGetUsers;
    private PreparedStatement psCheckUser;

    public boolean checkUser(String email, String password) throws SQLException{
            boolean b = false;
            psCheckUser = prepareStatement(checkUser);
            psCheckUser.setString(1, email);
            psCheckUser.setString(2, password);
            ResultSet rs = psCheckUser.executeQuery();
            if(rs.next()) {
                b =  true;
            }
        close(rs);
        return b;
    }

    @Override
    public List<User> getUserById(long id) throws SQLException {
        psGetAll = prepareStatement(getUserByIdQuery);
        psGetAll.setLong(1, id);
        psGetAll.execute();
        List<User> list = new ArrayList<>();
        ResultSet rs = psGetAll.getResultSet();
        while (rs.next()) {
            list.add(populateEntity(rs));
        }
        close(rs);
        return list;
    }

    @Override
    public User save(User user) throws SQLException {
        psSave = prepareStatement(saveQuery, Statement.RETURN_GENERATED_KEYS);
        psSave.setString(1, user.getLastName());
        psSave.setString(2, user.getFirstName());
        psSave.setString(3, user.getPassword());
        psSave.setString(4, user.getLogin());
        psSave.setString(5, "admin");
        psSave.setString(6, user.getEmail());
        psSave.setDate(7,  user.getDateOfBirth());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            user.setId(rs.getLong(1));
        }
        close(rs);
        return user;
    }

    @Override
    public User get(Serializable id) throws SQLException {
        psGet = prepareStatement(getQuery);
        psGet.setLong(1, (long) id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        if (rs.next()) {
            return populateEntity(rs);
        }
        close(rs);

        return null;
    }

    @Override
    public void update(User user) throws SQLException {
        psUpdate = prepareStatement(updateQuery);
        psUpdate.setLong(1, user.getId());
        psUpdate.setString(2, user.getLastName());
        psUpdate.setString(3, user.getFirstName());
        psUpdate.setString(4, user.getPassword());
        psUpdate.setString(5, user.getLogin());
        psUpdate.setString(6, user.getRole());
        psUpdate.setString(7, user.getEmail());
        psUpdate.setDate(8,  user.getDateOfBirth());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDelete = prepareStatement(deleteQuery);
        psDelete.setLong(1, (long) id);
        return psDelete.executeUpdate();
    }

    private User populateEntity(ResultSet rs) throws SQLException {
        User entity = new User();
        entity.setId(rs.getLong(1));
        entity.setLastName(rs.getString(2));
        entity.setFirstName(rs.getString(3));
        entity.setPassword(rs.getString(4));
        entity.setLogin(rs.getString(5));
        entity.setRole(rs.getString(6));
        entity.setEmail(rs.getString(7));
        entity.setDateOfBirth(rs.getDate(8));
        return entity;
    }

    public List<User> getAllUsers() throws SQLException {
        psGetUsers = prepareStatement(getUsers);
        psGetUsers.execute();
        List<User> list = new ArrayList<>();
        ResultSet rs = psGetUsers.getResultSet();
        while (rs.next()) {
            list.add(populateEntity(rs));
        }
        close(rs);
        return list;
    }


    public static UserDao getInstance() {
        UserDao userDao = INSTANCE;
        if (userDao == null) {
            synchronized (UserDaoImpl.class) {
                userDao = INSTANCE;
                if (userDao == null) {
                    INSTANCE = userDao = new UserDaoImpl();
                }
            }
        }
        return userDao;
    }
}
