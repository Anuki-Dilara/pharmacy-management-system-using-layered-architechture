package lk.ijse.carepoint.dao.custom.impl;

import lk.ijse.carepoint.dao.CrudUtil;
import lk.ijse.carepoint.dao.custom.CustomerDAO;
import lk.ijse.carepoint.dao.custom.OrderDAO;
import lk.ijse.carepoint.dto.CartDTO;
import lk.ijse.carepoint.entity.Cart;
import lk.ijse.carepoint.entity.Order;
import lk.ijse.carepoint.entity.PlaceOrder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Order entity) throws SQLException, ClassNotFoundException {
        String sql = "Insert Into customerorder values(?,?,?,?)";
        return CrudUtil.execute(sql, entity.getOrder_id(),entity.getOrder_amount(),entity.getOrder_date(),entity.getCustomer_id());
    }

    @Override
    public boolean update(Order entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveOrderDetail(ArrayList<Cart> cartdetail, PlaceOrder placeOrder) throws SQLException {
        for (Cart cartDTO : cartdetail){
            if (!saveorder(cartDTO,placeOrder)){
                return false;
            }
        }
        return true;
    }

    @Override
    public String generateNextOrderId() throws SQLException, ClassNotFoundException {
        String lastOrderId=generateorderId();
        if(lastOrderId==null){
            return "O0001";
        }else{
            String[] split=lastOrderId.split("[O]");
            int lastDigits=Integer.parseInt(split[1]);
            lastDigits++;
            String newOrderId=String.format("O%04d", lastDigits);
            return newOrderId;
        }
    }

    private String generateorderId()throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT customerOrder_id FROM customerorder ORDER BY customerOrder_id DESC LIMIT 1");
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }

    private boolean saveorder(Cart cartDTO, PlaceOrder placeOrder) throws SQLException {
        String sql ="Insert Into customer_order_medicine values(?,?,?)";
        return CrudUtil.execute(sql,placeOrder.getOrder_id(),cartDTO.getMedicine_id(),cartDTO.getCustomerOrder_quantity());
    }
}
