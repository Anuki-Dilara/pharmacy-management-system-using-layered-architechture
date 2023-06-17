package lk.ijse.carepoint.dao.custom;

import lk.ijse.carepoint.dao.SuperDAO;

import java.sql.SQLException;

public interface LoginDAO extends SuperDAO {
    boolean userCheckedInDB(String username, String password) throws SQLException, ClassNotFoundException;
}
