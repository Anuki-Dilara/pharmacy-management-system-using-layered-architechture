package lk.ijse.carepoint.dao.custom;

import lk.ijse.carepoint.dao.CrudDAO;
import lk.ijse.carepoint.dto.CartDTO;
import lk.ijse.carepoint.entity.Cart;
import lk.ijse.carepoint.entity.Order;
import lk.ijse.carepoint.entity.PlaceOrder;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDAO extends CrudDAO<Order> {
    public  boolean saveOrderDetail(ArrayList<Cart> cartdetail, PlaceOrder placeOrder) throws SQLException;
    public String generateNextOrderId() throws SQLException, ClassNotFoundException;

}
