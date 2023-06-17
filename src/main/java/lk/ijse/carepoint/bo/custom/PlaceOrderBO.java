package lk.ijse.carepoint.bo.custom;

import lk.ijse.carepoint.bo.SuperBO;
import lk.ijse.carepoint.dto.PlaceOrderDTO;
import lk.ijse.carepoint.dto.SupplierOrderDTO;

import java.sql.SQLException;

public interface PlaceOrderBO extends SuperBO {
    public boolean PlaceOrder(PlaceOrderDTO placeOrder) throws SQLException,ClassNotFoundException;

    public boolean PlaceLoad(SupplierOrderDTO sod) throws SQLException,ClassNotFoundException;
    public String generateNextOrderId() throws SQLException, ClassNotFoundException;
}
