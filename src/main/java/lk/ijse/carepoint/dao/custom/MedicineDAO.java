package lk.ijse.carepoint.dao.custom;

import lk.ijse.carepoint.dao.CrudDAO;
import lk.ijse.carepoint.dto.CartDTO;
import lk.ijse.carepoint.entity.Cart;
import lk.ijse.carepoint.entity.Detail;
import lk.ijse.carepoint.entity.Medicine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface MedicineDAO extends CrudDAO<Medicine> {
    public ArrayList<String> getMedicineIDs() throws SQLException, ClassNotFoundException;

    public boolean updateQty(List<Cart> cartDTOList) throws SQLException;

    boolean updateLoadQty(List<Detail> details) throws SQLException;
}
