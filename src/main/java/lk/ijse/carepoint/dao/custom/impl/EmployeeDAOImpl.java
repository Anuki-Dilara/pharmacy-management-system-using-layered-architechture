package lk.ijse.carepoint.dao.custom.impl;

import lk.ijse.carepoint.dao.CrudUtil;
import lk.ijse.carepoint.dao.custom.EmployeeDAO;
import lk.ijse.carepoint.entity.Customer;
import lk.ijse.carepoint.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM employee";

        ArrayList<Employee> empData = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            empData.add(new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    resultSet.getInt(6)

            ));
        }
        return empData;
    }

    @Override
    public boolean add(Employee entity) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO employee(employee_id,employee_name,employee_address,employee_contaactNumber,employee_salary,employee_age) VALUES(?,?,?,?)";
        return CrudUtil.execute(sql, entity.getEmployee_id(), entity.getEmployee_name(), entity.getEmployee_address(), entity.getEmployee_contactNumber(),entity.getEmployee_salary(),entity.getEmployee_age());

    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        String sql="UPDATE employee SET employee_name = ?, employee_address = ?,employee_contactNumber = ?,employee_salary = ?,employee_age = ? WHERE employee_id = ?";

        return CrudUtil.execute(sql,  entity.getEmployee_name(), entity.getEmployee_address(), entity.getEmployee_contactNumber(),entity.getEmployee_salary(),entity.getEmployee_age(),entity.getEmployee_id());

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
        String sql="DELETE FROM customer WHERE employee_id=? ";

        return CrudUtil.execute(sql,id);
    }

    @Override
    public Employee search(String id) throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM employee WHERE employee_id= ?";

        ResultSet resultSet = CrudUtil.execute(sql, id);

        if(resultSet.next()) {
            String  employee_id = resultSet.getString(1);
            String employee_name = resultSet.getString(2);
            String employee_address = resultSet.getString(3);
            String employee_contactNumber =resultSet.getString(4);
            Double employee_salary =resultSet.getDouble(5);
            Integer employee_age = Integer.valueOf(resultSet.getString(6));

            return new Employee(employee_id, employee_name, employee_address,employee_contactNumber,employee_salary,employee_age);
        }
        return null;
    }

    @Override
    public ArrayList<String> getIDs() throws SQLException, ClassNotFoundException {
        ArrayList<String> ids = new ArrayList<>();

        String sql = "SELECT employee_id FROM employee";

        ResultSet resultSet=CrudUtil.execute(sql);

        while(resultSet.next()) {
            ids.add(resultSet.getString(1));
        }
        return ids;
    }
}
