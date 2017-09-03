package dao.impl;

import dao.ItemDao;
import entities.Item;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 9/1/17.
 */
public class ItemDaoImpl extends AbstractDao implements ItemDao {

    private static volatile ItemDao INSTANCE = null;

    private static final String saveQuery = "INSERT INTO `ITEM` (ITEM_ID, BRAND, MODEL, PRICE) VALUES (?, ?, ?, ?)";
    private static final String updateQuery = "UPDATE `ITEM` SET BRAND=?, MODEL=?, PRICE=? WHERE ITEM_ID=?";
    private static final String getQuery = "SELECT * FROM `ITEM` WHERE ITEM_ID=?";
    private static final String deleteQuery = "DELETE FROM `ITEM` WHERE ITEM_ID=?";
    private static final String getAll = "SELECT * FROM `ITEM`";

    private PreparedStatement psSave;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psGetAll;
    private PreparedStatement psDelete;

    @Override
    public List<Item> getAll() throws SQLException {
        psGetAll = prepareStatement(getAll);
        psGetAll.execute();
        List<Item> list = new ArrayList<>();
        ResultSet rs = psGetAll.getResultSet();
        while (rs.next()) {
            list.add(populateEntity(rs));
        }
        close(rs);
        return list;
    }

    public static ItemDao getInstance() {
        ItemDao itemDao = INSTANCE;
        if (itemDao == null) {
            synchronized (UserDaoImpl.class) {
                itemDao = INSTANCE;
                if (itemDao == null) {
                    INSTANCE = itemDao = new ItemDaoImpl();
                }
            }
        }
        return itemDao;
    }

    @Override
    public Item save(Item item) throws SQLException {
        psSave = prepareStatement(saveQuery, Statement.RETURN_GENERATED_KEYS);
        psSave.setLong(1, item.getItemId());
        psSave.setString(2, item.getBrand());
        psSave.setString(3, item.getModel());
        psSave.setDouble(4, item.getPrice());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            item.setItemId(rs.getLong(1));
        }
        close(rs);
        return item;
    }

    @Override
    public Item get(Serializable id) throws SQLException {
        psGet = prepareStatement(getQuery);
        psGet.setLong(1, (long)id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        if (rs.next()) {
            return populateEntity(rs);
        }
        close(rs);
        return null;
    }

    @Override
    public void update(Item item) throws SQLException {
        psUpdate = prepareStatement(updateQuery);
        psUpdate.setLong(1, item.getItemId());
        psUpdate.setString(2, item.getBrand());
        psUpdate.setString(3, item.getModel());
        psUpdate.setDouble(4, item.getPrice());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDelete = prepareStatement(deleteQuery);
        psDelete.setLong(1, (long)id);
        return psDelete.executeUpdate();
    }

    private Item populateEntity(ResultSet rs) throws SQLException {
        Item entity = new Item();
        entity.setItemId(rs.getLong(1));
        entity.setBrand(rs.getString(2));
        entity.setModel(rs.getString(3));
        entity.setPrice(rs.getDouble(4));
        return entity;
    }
}
