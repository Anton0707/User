package services.impl;

import java.sql.Connection;
import java.sql.SQLException;

import db.ConnectionManager;
import db.DbManagerException;

/**
 * Created by yslabko on 08/03/2017.
 */
public abstract class AbstractService {

    public void startTransaction() throws SQLException {
        ConnectionManager.getConnection().setAutoCommit(false);
    }

    public void commit() throws SQLException {
        ConnectionManager.getConnection().commit();
    }

    public Connection getConnection() {
        return ConnectionManager.getConnection();
    }

    public void rollback() {
        try {
            getConnection().rollback();
        } catch (SQLException e) {
            throw new DbManagerException("rollback error");
        }
    }
}
