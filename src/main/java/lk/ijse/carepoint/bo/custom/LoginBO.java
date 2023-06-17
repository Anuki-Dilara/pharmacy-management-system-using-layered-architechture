package lk.ijse.carepoint.bo.custom;

import lk.ijse.carepoint.bo.SuperBO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    boolean userCheckedInDB(String username, String password) throws SQLException, ClassNotFoundException;
}
