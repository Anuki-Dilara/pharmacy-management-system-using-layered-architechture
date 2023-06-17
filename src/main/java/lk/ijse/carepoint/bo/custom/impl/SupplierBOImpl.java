package lk.ijse.carepoint.bo.custom.impl;

import lk.ijse.carepoint.bo.custom.SupplierBO;
import lk.ijse.carepoint.dao.CrudUtil;
import lk.ijse.carepoint.dao.DAOFactory;
import lk.ijse.carepoint.dao.custom.CustomerDAO;
import lk.ijse.carepoint.dao.custom.SupplierDAO;
import lk.ijse.carepoint.dto.CustomerDTO;
import lk.ijse.carepoint.dto.SupplierDTO;
import lk.ijse.carepoint.entity.Customer;
import lk.ijse.carepoint.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    private SupplierDAO supplierDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    @Override
    public ArrayList<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierDTO> allSuppliers= new ArrayList<>();
        ArrayList<Supplier> all = supplierDAO.getAll();
        for (Supplier supplier : all) {
            allSuppliers.add(new SupplierDTO(supplier.getSupplier_id(),supplier.getSupplier_name(),supplier.getSupplier_address(),supplier.getSupplier_phoneNumber()));
        }
        return allSuppliers;
    }

    @Override
    public boolean addSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.add(new Supplier(
                dto.getSupplier_id(),
                dto.getSupplier_name(),
                dto.getSupplier_address(),
                dto.getSupplier_phoneNumber()

        ));
    }

    @Override
    public boolean updateSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(
                dto.getSupplier_name(),
                dto.getSupplier_address(),
                dto.getSupplier_phoneNumber(),
                dto.getSupplier_id()
        ));
    }

    @Override
    public boolean existSupplier(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }

    @Override
    public String generateNewSupplierID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public SupplierDTO searchSupplier(String id) throws SQLException, ClassNotFoundException {
        Supplier search = supplierDAO.search(id);
        return new SupplierDTO(search.getSupplier_id(),search.getSupplier_name(),search.getSupplier_address(),
                search.getSupplier_phoneNumber());
    }

    @Override
    public ArrayList<String> getIDs() throws SQLException, ClassNotFoundException {
        ArrayList<String> ids = new ArrayList<>();

        ArrayList<String> all = supplierDAO.getIDs();
        for (String id : all) {
            ids.add(id);
        }
        return ids;
    }
}
