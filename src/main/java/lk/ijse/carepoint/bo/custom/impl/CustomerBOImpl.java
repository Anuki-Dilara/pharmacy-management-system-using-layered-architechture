package lk.ijse.carepoint.bo.custom.impl;

import lk.ijse.carepoint.bo.SuperBO;
import lk.ijse.carepoint.bo.custom.CustomerBO;
import lk.ijse.carepoint.dao.CrudUtil;
import lk.ijse.carepoint.dao.DAOFactory;
import lk.ijse.carepoint.dao.custom.CustomerDAO;
import lk.ijse.carepoint.dto.CustomerDTO;
import lk.ijse.carepoint.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    private CustomerDAO customerDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomers= new ArrayList<>();
        ArrayList<Customer> all = customerDAO.getAll();
        for (Customer customer : all) {
            allCustomers.add(new CustomerDTO(customer.getCustomer_id(),customer.getCustomer_name(),customer.getCustomer_address(),customer.getCustomer_age(),customer.getPhone_number()));
        }
        return allCustomers;
    }

    @Override
    public boolean addCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.add(new Customer(
                dto.getCustomer_id(),
                dto.getCustomer_name(),
                dto.getCustomer_address(),
                dto.getCustomer_age(),
                dto.getPhone_number()
        ));
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(
                dto.getCustomer_name(),
                dto.getCustomer_address(),
                dto.getPhone_number(),
                dto.getCustomer_age(),
                dto.getCustomer_id()
        ));
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public String generateNewCustomerID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.search(id);
        return new CustomerDTO(customer.getCustomer_id(),customer.getCustomer_name(),customer.getCustomer_address(),
                customer.getCustomer_age(),customer.getPhone_number());
    }


    @Override
    public ArrayList<String> getIDs() throws SQLException, ClassNotFoundException {
        ArrayList<String> ids = new ArrayList<>();
        ArrayList<String> all = customerDAO.getIDs();
        for (String id : all) {
            ids.add(id);
        }
        return ids;

    }
}
