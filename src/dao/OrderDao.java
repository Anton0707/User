package dao;

import dao.DAO;
import entities.Order;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by admin on 9/3/17.
 */
public interface OrderDao extends DAO<Order> {
    List<Order> getByUserId(long userId) throws SQLException;
}
