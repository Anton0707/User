package dao.impl;

import dao.OrderDao;
import entities.Item;
import entities.Order;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 9/3/17.
 */
public class OrderDaoImpl extends  AbstractDao implements OrderDao {

    private static volatile OrderDao INSTANCE = null;

    private static final String saveQuery = "INSERT INTO `ORDER` (USER_ID, DATE) VALUES (?, ?)";
    private static final String updateQuery = "UPDATE `ORDER` SET DATE=? WHERE ORDER_ID=?";
    private static final String getQuery = "SELECT * FROM `ORDER` WHERE ORDER_ID=?";
    private static final String deleteQuery = "DELETE FROM `ORDER` WHERE ORDER_ID=?";
    private static final String getAllByUserQuery = "SELECT ORDER_ID, USER_ID FROM `ORDER` WHERE USER_ID = ? ORDER BY ORDER_ID DESC";

    private PreparedStatement psSave;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psDelete;
    private PreparedStatement psGetAllByUserId;


    @Override
    public Order save(Order order) throws SQLException {
        psSave = prepareStatement(saveQuery, Statement.RETURN_GENERATED_KEYS);
        psSave.setLong(1, order.getOrderId());
        psSave.setLong(2, order.getUserId());
        psSave.setDate(3, order.getDate());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            order.setOrderId(rs.getLong(1));
        }
        close(rs);
        return order;
    }

    @Override
    public Order get(Serializable id) throws SQLException {
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
    public void update(Order order) throws SQLException {
        psUpdate = prepareStatement(updateQuery);
        psUpdate.setLong(1, order.getOrderId());
        psUpdate.setLong(2, order.getUserId());
        psUpdate.setDate(3, order.getDate());;
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDelete = prepareStatement(deleteQuery);
        psDelete.setLong(1, (long) id);
        return psDelete.executeUpdate();
    }

    private Order populateEntity(ResultSet rs) throws SQLException {
        Order entity = new Order();
        entity.setOrderId(rs.getLong(1));
        entity.setUserId(rs.getLong(2));
        entity.setDate(rs.getDate(3));
        return entity;
    }

    public static OrderDao getInstance() {
        OrderDao orderDao = INSTANCE;
        if (orderDao == null) {
            synchronized (UserDaoImpl.class) {
                orderDao = INSTANCE;
                if (orderDao == null) {
                    INSTANCE = orderDao = new OrderDaoImpl();
                }
            }
        }
        return orderDao;
    }

    @Override
    public List<Order> getByUserId(long userId) throws SQLException {
        psGetAllByUserId = prepareStatement(getAllByUserQuery);
        psGetAllByUserId.setLong(1, userId);
        psGetAllByUserId.execute();
        ResultSet rs = psGetAllByUserId.getResultSet();
        List<Order> list = new ArrayList<>();
        while (rs.next()) {
            list.add(populateEntity(rs));
        }
        close(rs);

        return list;
    }
}
