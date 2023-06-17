package lk.ijse.carepoint.bo.custom.impl;

import lk.ijse.carepoint.bo.custom.EmployeeBO;
import lk.ijse.carepoint.dao.CrudUtil;
import lk.ijse.carepoint.dao.DAOFactory;
import lk.ijse.carepoint.dao.custom.EmployeeDAO;
import lk.ijse.carepoint.dto.CustomerDTO;
import lk.ijse.carepoint.dto.EmployeeDTO;
import lk.ijse.carepoint.dto.SupplierDTO;
import lk.ijse.carepoint.entity.Customer;
import lk.ijse.carepoint.entity.Employee;
import lk.ijse.carepoint.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    private EmployeeDAO employeeDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EMPLOYEE);


    @Override
    public ArrayList<EmployeeDTO> getAllEmployees() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> allEmployees= new ArrayList<>();
        ArrayList<Employee> all = employeeDAO.getAll();
        for (Employee employee : all) {
            allEmployees.add(new EmployeeDTO(employee.getEmployee_id(),employee.getEmployee_name(),employee.getEmployee_address(),employee.getEmployee_contactNumber(),employee.getEmployee_salary(),employee.getEmployee_age()));
        }
        return allEmployees;
    }

    @Override
    public boolean addEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.add(new Employee(
                dto.getEmployee_id(),
                dto.getEmployee_name(),
                dto.getEmployee_address(),
                dto.getEmployee_contactNumber(),
                dto.getEmployee_salary(),
                dto.getEmployee_age()
        ));
    }

    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(
                dto.getEmployee_id(),
                dto.getEmployee_name(),
                dto.getEmployee_address(),
                dto.getEmployee_contactNumber(),
                dto.getEmployee_salary(),
                dto.getEmployee_age()

        ));
    }

    @Override
    public boolean existEmployee(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public String generateNewEmployeeID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public EmployeeDTO searchEmployee(String id) throws SQLException, ClassNotFoundException {
        Employee search = employeeDAO.search(id);
        return new EmployeeDTO(search.getEmployee_id(),search.getEmployee_name(),search.getEmployee_address(),
                search.getEmployee_contactNumber(),search.getEmployee_salary(),search.getEmployee_age());
    }

    @Override
    public ArrayList<String> getIDs() throws SQLException, ClassNotFoundException {
        ArrayList<String> ids = new ArrayList<>();
        ArrayList<String> all = employeeDAO.getIDs();
        for (String id : all) {
            ids.add(id);
        }
        return ids;
    }
}
