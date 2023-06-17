package lk.ijse.carepoint.dao.custom;

import lk.ijse.carepoint.dao.CrudDAO;
import lk.ijse.carepoint.entity.Detail;
import lk.ijse.carepoint.entity.SupplierOrder;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierOrderDAO extends CrudDAO<SupplierOrder> {
    boolean saveOrderDetail(ArrayList<Detail> details, SupplierOrder s1) throws SQLException;
}
