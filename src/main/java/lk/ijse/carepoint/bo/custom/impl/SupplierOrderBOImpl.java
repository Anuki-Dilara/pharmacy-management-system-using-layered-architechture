package lk.ijse.carepoint.bo.custom.impl;

import lk.ijse.carepoint.bo.custom.SupplierOrderBO;
import lk.ijse.carepoint.dao.DAOFactory;
import lk.ijse.carepoint.dao.custom.SupplierDAO;
import lk.ijse.carepoint.dao.custom.SupplierOrderDAO;

import java.sql.SQLException;

public class SupplierOrderBOImpl implements SupplierOrderBO {
    private SupplierOrderDAO supplierDAO=  DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPORDER);
    @Override
    public String generateNewLoadID() throws SQLException, ClassNotFoundException {
        return supplierDAO.generateNewID();
    }
}
