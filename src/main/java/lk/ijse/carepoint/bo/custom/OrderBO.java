package lk.ijse.carepoint.bo.custom;

import lk.ijse.carepoint.bo.SuperBO;

import java.sql.SQLException;

public interface OrderBO extends SuperBO {
    public String generateNewOrderID() throws SQLException, ClassNotFoundException;

}
