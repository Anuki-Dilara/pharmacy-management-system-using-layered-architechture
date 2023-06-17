package lk.ijse.carepoint.dao.custom.impl;

import lk.ijse.carepoint.dao.CrudUtil;
import lk.ijse.carepoint.dao.custom.SupplierDAO;
import lk.ijse.carepoint.entity.Customer;
import lk.ijse.carepoint.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM supplier";

        ArrayList<Supplier> supData = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            supData.add(new Supplier(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return supData;
    }

    @Override
    public boolean add(Supplier entity) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO supplier(supplier_id,supplier_name,supplier_address,Supplier_phoneNumber) VALUES(?,?,?,?)";

        return CrudUtil.execute(sql, entity.getSupplier_id(), entity.getSupplier_name(), entity.getSupplier_address(), entity.getSupplier_phoneNumber());

    }

    @Override
    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {
        String sql="UPDATE supplier SET supplier_name = ?, supplier_address = ?, supplier_phoneNumber = ? WHERE supplier_id = ?";

        return CrudUtil.execute(sql,  entity.getSupplier_name(), entity.getSupplier_address(), entity.getSupplier_phoneNumber(),entity.getSupplier_id());

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
        String sql="DELETE FROM supplier WHERE supplier_id=? ";

        return CrudUtil.execute(sql,id);
    }

    @Override
    public Supplier search(String id) throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM supplier WHERE supplier_id= ?";

        ResultSet resultSet = CrudUtil.execute(sql, id);

        if(resultSet.next()) {
            String  supplier_id = resultSet.getString(1);
            String supplier_name = resultSet.getString(2);
            String supplier_address = resultSet.getString(3);
            String supplier_phoneNumber =resultSet.getString(4);

            return new Supplier(supplier_id, supplier_name, supplier_address,supplier_phoneNumber);
        }
        return null;
    }

    @Override
    public ArrayList<String> getIDs() throws SQLException, ClassNotFoundException {
        ArrayList<String> ids = new ArrayList<>();

        String sql = "SELECT supplier_id FROM supplier";

        ResultSet resultSet=CrudUtil.execute(sql);

        while(resultSet.next()) {
            ids.add(resultSet.getString(1));
        }
        return ids;
    }
}
