package lk.ijse.carepoint.dao.custom.impl;

import lk.ijse.carepoint.dao.CrudUtil;
import lk.ijse.carepoint.dao.custom.MedicineDAO;
import lk.ijse.carepoint.dto.CartDTO;
import lk.ijse.carepoint.entity.Cart;
import lk.ijse.carepoint.entity.Detail;
import lk.ijse.carepoint.entity.Employee;
import lk.ijse.carepoint.entity.Medicine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicineDAOImpl implements MedicineDAO {
    @Override
    public ArrayList<Medicine> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM medicine";

        ArrayList<Medicine> mediData = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            mediData.add(new Medicine(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getInt(5),
                    resultSet.getString(6)

            ));
        }
        return mediData;
    }

    @Override
    public boolean add(Medicine entity) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO medicine(medicine_id,medicine_name,medicine_details,unit_price,qauntity,supplierid) VALUES(?,?,?,?,?,?)";

        return CrudUtil.execute(sql, entity.getMedicine_id(), entity.getMedicine_name(), entity.getDescription(),
                entity.getUnit_price(),entity.getQuantity(),entity.getSupplierid());

    }

    @Override
    public boolean update(Medicine entity) throws SQLException, ClassNotFoundException {
        String sql="UPDATE medicine SET medicine_name = ?, medicine_details = ?,unit_price = ?,quantity = ? , " +
                "supplierid =? WHERE medicine_id = ?";

        return CrudUtil.execute(sql,  entity.getMedicine_name(), entity.getDescription(), entity.getUnit_price(),
                entity.getQuantity(),entity.getSupplierid(),entity.getMedicine_id());

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
        String sql="DELETE FROM medicine WHERE medicine_id=? ";

        return CrudUtil.execute(sql,id);
    }


    @Override
    public Medicine search(String id) throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM medicine WHERE medicine_id= ?";

        ResultSet resultSet = CrudUtil.execute(sql, id);

        if(resultSet.next()) {
            String  medicine_id = resultSet.getString(1);
            String medicine_name = resultSet.getString(2);
            String medicine_details = resultSet.getString(3);
            Double unit_price = Double.valueOf(resultSet.getString(4));
            Integer quantity =resultSet.getInt(5);
            String  supplier_id = resultSet.getString(6);


            return new Medicine(medicine_id, medicine_name, medicine_details,unit_price,quantity,supplier_id);
        }
        return null;
    }

    @Override
    public ArrayList<String> getMedicineIDs() throws SQLException, ClassNotFoundException {
        ArrayList<String> ids = new ArrayList<>();

        String sql = "SELECT medicine_id FROM medicine";

        ResultSet resultSet=CrudUtil.execute(sql);

        while(resultSet.next()) {
            ids.add(resultSet.getString(1));
        }
        return ids;
    }

    @Override
    public boolean updateQty(List<Cart> cartDTOList) throws SQLException {
        for (Cart cartDTO : cartDTOList){
            if (!updateQty(new Medicine(cartDTO.getMedicine_id(),cartDTO.getCustomerOrder_quantity()))){
                return false;
            }
        }
        return true;
    }
    private static boolean updateQty(Medicine m1) throws SQLException {
        String sql = "Update medicine set quantity = quantity-? Where medicine_id=?";
        return CrudUtil.execute(sql,m1.getQuantity(),m1.getMedicine_id());
    }

    @Override
    public boolean updateLoadQty(List<Detail> details) throws SQLException {
        for (Detail detail : details){
            if (!updateLoadQty(new Medicine(detail.getMed_id(),detail.getQty()))){
                return false;
            }
        }
        return true;
    }
    private static boolean updateLoadQty(Medicine m1) throws SQLException {
        String sql = "Update medicine set quantity = quantity + ? Where medicine_id=?";
        return CrudUtil.execute(sql,m1.getQuantity(),m1.getMedicine_id());
    }


}
