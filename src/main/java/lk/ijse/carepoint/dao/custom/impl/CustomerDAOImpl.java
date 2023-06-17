package lk.ijse.carepoint.dao.custom.impl;

import lk.ijse.carepoint.dao.CrudUtil;
import lk.ijse.carepoint.dao.custom.CustomerDAO;
import lk.ijse.carepoint.db.DBConnection;
import lk.ijse.carepoint.entity.Customer;
//import lk.ijse.carepoint.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {


    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM customer";

        ArrayList<Customer> cusData = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            cusData.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5)
            ));
        }
        return cusData;
    }

    @Override
    public boolean add(Customer entity) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO customer(customer_id,customer_name,customer_address,customer_age,phone_number) VALUES(?,?,?,?)";

        return CrudUtil.execute(sql,
                entity.getCustomer_id(),
                entity.getCustomer_name(),
                entity.getCustomer_address(),
                entity.getCustomer_age(),
                entity.getPhone_number());
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        String sql="UPDATE customer SET customer_name = ?, customer_address = ?,customer_age = ?, phone_number = ? WHERE customer_id = ?";

        return CrudUtil.execute(sql,  entity.getCustomer_name(), entity.getCustomer_address(), entity.getCustomer_age(),entity.getPhone_number(),entity.getCustomer_id());
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
        String sql="DELETE FROM customer WHERE customer_id=? ";

        return CrudUtil.execute(sql,id);
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM customer WHERE customer_id= ?";

        ResultSet resultSet = CrudUtil.execute(sql, id);

        if(resultSet.next()) {
            String  customer_id = resultSet.getString(1);
            String customer_name = resultSet.getString(2);
            String customer_address = resultSet.getString(3);
            Integer customer_age = Integer.valueOf(resultSet.getString(4));
            String phone_number =resultSet.getString(5);

            return new Customer(customer_id, customer_name, customer_address,customer_age,phone_number);
        }
        return null;
    }

    @Override
    public ArrayList<String> getIDs() throws SQLException, ClassNotFoundException {
        ArrayList<String> ids = new ArrayList<>();

        String sql = "SELECT customer_id FROM customer";

        ResultSet resultSet=CrudUtil.execute(sql);

        while(resultSet.next()) {
            ids.add(resultSet.getString(1));
        }
        return ids;
    }
}
