package dao.impl;

import dao.OrderToItemDao;
import entities.OrderToItem;

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
public class OrderToItemDaoImpl extends AbstractDao implements OrderToItemDao {

    private static volatile OrderToItemDao INSTANCE = null;

    private static final String saveQuery = "INSERT INTO `ORDER_TO_ITEM` (ORDER_ID, ITEM_ID) VALUES (?, ?)";
    private static final String getItemsByOrderQuery = "SELECT * FROM `ORDER_TO_ITEM` WHERE ORDER_ID = ?";

    private PreparedStatement psSave;
    private PreparedStatement psGetAll;


    @Override
    public OrderToItem save(OrderToItem orderToItem) throws SQLException {
        psSave = prepareStatement(saveQuery, Statement.RETURN_GENERATED_KEYS);
        psSave.setLong(1, orderToItem.getOrderId());
        psSave.setLong(2, orderToItem.getOrderId());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            orderToItem.setOrderId(rs.getLong(1));
        }
        close(rs);
        return orderToItem;
    }

    @Override
    public OrderToItem get(Serializable id) throws SQLException {
        return null;
    }

    @Override
    public void update(OrderToItem orderToItem) throws SQLException {

    }


    @Override
    public int delete(Serializable id) throws SQLException {
        return 0;
    }




    private OrderToItem populateEntity(ResultSet rs) throws SQLException {
        OrderToItem entity = new OrderToItem();
        entity.setOrderId(rs.getLong(1));
        entity.setItemId(rs.getLong(2));
        return entity;
    }

    public static OrderToItemDao getInstance() {
        OrderToItemDao orderToItemDao = INSTANCE;
        if (orderToItemDao == null) {
            synchronized (UserDaoImpl.class) {
                orderToItemDao = INSTANCE;
                if (orderToItemDao == null) {
                    INSTANCE = orderToItemDao = new OrderToItemDaoImpl();
                }
            }
        }
        return orderToItemDao;
    }

    @Override
    public List<OrderToItem> getByOrderId(long orderId) throws SQLException {
        psGetAll = prepareStatement(getItemsByOrderQuery);
        psGetAll.setLong(1, orderId);
        psGetAll.execute();
        List<OrderToItem> list = new ArrayList<>();
        ResultSet rs = psGetAll.getResultSet();
        while (rs.next()) {
            list.add(populateEntity(rs));
        }
        close(rs);
        return list;
    }
}
