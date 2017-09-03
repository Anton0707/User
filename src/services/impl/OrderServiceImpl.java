package services.impl;

import dao.ItemDao;
import dao.OrderDao;
import dao.OrderToItemDao;
import dao.impl.ItemDaoImpl;
import dao.impl.OrderDaoImpl;
import dao.impl.OrderToItemDaoImpl;
import entities.Order;
import entities.OrderToItem;
import services.OrderService;
import services.ServiceException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by admin on 9/3/17.
 */
public class OrderServiceImpl extends AbstractService implements OrderService {
    private static volatile OrderService INSTANCE = null;

    private OrderDao orderDao = OrderDaoImpl.getInstance();
    private ItemDao itemDao = ItemDaoImpl.getInstance();
    private OrderToItemDao orderToItemDao = OrderToItemDaoImpl.getInstance();

    @Override
    public Order createOrder(long userId, long itemId) {
        Order order = new Order();
        try {
            startTransaction();
            order.setUserId(userId);

            order = orderDao.save(order);

            OrderToItem orderToItem = new OrderToItem(order.getOrderId(),itemId);
            orderToItemDao.save(orderToItem);
            commit();
            return order;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error creating Order " + order, e);
        }
    }

    @Override
    public Order get(Serializable id) {
        try {
            return orderDao.get(id);
        } catch (SQLException e) {
            throw new ServiceException("Error getting Order by id" + id);
        }
    }

    @Override
    public void update(Order order) {
        try {
            orderDao.update(order);
        } catch (SQLException e) {
            throw new ServiceException("Error updating Order " + order);
        }
    }

    @Override
    public int delete(Serializable id) {
        try {
            return orderDao.delete(id);
        } catch (SQLException e) {
            throw new ServiceException("Error deleting Order by id" + id);
        }
    }

    @Override
    public List<Order> getByUserId(long userId) {
        try {
            startTransaction();
            List<Order> orders = orderDao.getByUserId(userId);
            for (Order order : orders) {
                List<OrderToItem> orderToItems = orderToItemDao.getByOrderId(order.getOrderId());
                order.setOrderToItems(orderToItems);
                commit();
            }
            return orders;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error getting Orders by userId" + userId);
        }
    }

    public static OrderService getInstance() {
        OrderService itemService = INSTANCE;
        if (itemService == null) {
            synchronized (ItemDaoImpl.class) {
                itemService = INSTANCE;
                if (itemService == null) {
                    INSTANCE = itemService = new OrderServiceImpl();
                }
            }
        }

        return itemService;
    }
}
