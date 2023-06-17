package lk.ijse.carepoint.dao.custom;

import lk.ijse.carepoint.dao.CrudDAO;
import lk.ijse.carepoint.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDAO extends CrudDAO<Employee> {
    ArrayList<String> getIDs() throws SQLException, ClassNotFoundException;
}
