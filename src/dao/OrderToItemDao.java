package dao;

import entities.OrderToItem;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by admin on 9/3/17.
 */
public interface OrderToItemDao extends DAO<OrderToItem> {
    List<OrderToItem> getByOrderId(long orderId) throws SQLException;
}
