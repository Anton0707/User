package services;

import entities.Order;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 9/3/17.
 */
public interface OrderService {
    Order createOrder(long userId, long itemId);
    Order get(Serializable id);
    void update(Order order);
    int delete(Serializable id);

    List<Order> getByUserId(long userId);
}
