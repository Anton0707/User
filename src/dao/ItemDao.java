package dao;

import entities.Item;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by admin on 9/1/17.
 */
public interface ItemDao extends DAO<Item> {
    List<Item> getAll() throws SQLException;
}
