package lk.ijse.carepoint.dao.custom.impl;

import lk.ijse.carepoint.dao.CrudUtil;
import lk.ijse.carepoint.dao.custom.SupplierOrderDAO;
import lk.ijse.carepoint.entity.Detail;
import lk.ijse.carepoint.entity.SupplierOrder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderDAOImpl implements SupplierOrderDAO {
    @Override
    public ArrayList<SupplierOrder> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(SupplierOrder entity) throws SQLException, ClassNotFoundException {
        String sql = "Insert Into supplierorder values(?,?,?,?)";
        return CrudUtil.execute(sql,entity.getSu_op_id(),entity.getDate(),entity.getAmount(),entity.getSupid());
    }

    @Override
    public boolean update(SupplierOrder entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        String lastOrderId=generateLoadId();
        if(lastOrderId==null){
            return "L0001";
        }else{
            String[] split=lastOrderId.split("[L]");
            int lastDigits=Integer.parseInt(split[1]);
            lastDigits++;
            String newOrderId=String.format("L%04d", lastDigits);
            return newOrderId;
        }
    }
    private static String generateLoadId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT supplierOrder_id FROM supplierorder ORDER BY supplierOrder_id DESC LIMIT 1");
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public SupplierOrder search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveOrderDetail(ArrayList<Detail> details, SupplierOrder s1) throws SQLException {
        for (Detail detail : details){
            if (!saveorder(detail,s1)){
                return false;
            }
        }
        return true;
    }
    private static boolean saveorder(Detail detail, SupplierOrder supplierOrder) throws SQLException {
        String sql ="Insert Into supplier_order_medicine values(?,?,?,?)";
        return CrudUtil.execute(sql,supplierOrder.getSu_op_id(),detail.getMed_id(),detail.getQty(),detail.getAmount());
    }
}
