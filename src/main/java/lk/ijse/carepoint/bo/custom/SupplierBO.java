package lk.ijse.carepoint.bo.custom;

import lk.ijse.carepoint.bo.SuperBO;
import lk.ijse.carepoint.dto.CustomerDTO;
import lk.ijse.carepoint.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    public ArrayList<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException;

    public boolean addSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean updateSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean existSupplier(String id) throws SQLException, ClassNotFoundException;

    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException;

    public String generateNewSupplierID() throws SQLException, ClassNotFoundException;

    public SupplierDTO searchSupplier(String id) throws SQLException, ClassNotFoundException;

    public ArrayList<String> getIDs() throws SQLException, ClassNotFoundException;
}
