package services.impl;

import dao.ItemDao;
import dao.impl.ItemDaoImpl;
import entities.Item;
import services.ItemService;
import services.ServiceException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by admin on 9/1/17.
 */
public class ItemServiceImpl extends AbstractService implements ItemService {

    private static volatile ItemService INSTANCE = null;

    private ItemDao itemDao = ItemDaoImpl.getInstance();

    private ItemServiceImpl(){

    }

    @Override
    public Item save(Item item) {
        try {
            item = itemDao.save(item);
        } catch (SQLException e) {
            throw new ServiceException("Error creating Item" + item);
        }
        return item;
    }

    @Override
    public Item get(Serializable id) {
        try {
            return itemDao.get(id);
        } catch (SQLException e) {
            throw new ServiceException("Error geting Item by id " + id);
        }
    }

    @Override
    public void update(Item item) {
        try {
            itemDao.update(item);
        } catch (SQLException e) {
            throw new ServiceException("Error updating Item" + item);
        }
    }

    @Override
    public int delete(Serializable id) {
        return 0;
    }

    @Override
    public List<Item> getAll() throws SQLException {
        return itemDao.getAll();
    }
    public static ItemService getInstance() {
        ItemService itemService = INSTANCE;
        if (itemService == null) {
            synchronized (ItemDaoImpl.class) {
                itemService = INSTANCE;
                if (itemService == null) {
                    INSTANCE = itemService = new ItemServiceImpl();
                }
            }
        }
        return itemService;
    }
}
