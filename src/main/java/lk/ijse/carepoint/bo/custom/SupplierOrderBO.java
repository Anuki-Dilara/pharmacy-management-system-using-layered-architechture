package lk.ijse.carepoint.bo.custom;

import lk.ijse.carepoint.bo.SuperBO;

import java.sql.SQLException;

public interface SupplierOrderBO extends SuperBO {
    String generateNewLoadID() throws SQLException, ClassNotFoundException;
}
