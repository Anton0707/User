package services;

import entities.Item;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by admin on 9/1/17.
 */
public interface ItemService {
    Item save(Item item);

    Item get(Serializable id);

    void update(Item item);

    int delete(Serializable id);

    List<Item> getAll() throws SQLException;
}
