package lk.ijse.carepoint.bo.custom.impl;

import lk.ijse.carepoint.bo.custom.OrderBO;
import lk.ijse.carepoint.dao.DAOFactory;
import lk.ijse.carepoint.dao.custom.OrderDAO;

import java.sql.SQLException;

public class OrderBOImpl implements OrderBO {
    private OrderDAO orderDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);
    @Override
    public String generateNewOrderID() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNextOrderId();
    }
}
