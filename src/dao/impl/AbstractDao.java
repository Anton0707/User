package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.ConnectionManager;

/**
 * Class AbstractDao
 *
 * Created by yslabko on 08/08/2017.
 */
public abstract class AbstractDao {

    protected PreparedStatement prepareStatement(String query) throws SQLException {
        return ConnectionManager.getConnection().prepareStatement(query);
    }

    protected PreparedStatement prepareStatement(String query, int flag) throws SQLException {
        return ConnectionManager.getConnection().prepareStatement(query, flag);
    }

    protected void close(ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
