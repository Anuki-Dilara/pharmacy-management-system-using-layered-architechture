package lk.ijse.carepoint.bo.custom.impl;

import lk.ijse.carepoint.bo.custom.PlaceOrderBO;
import lk.ijse.carepoint.dao.DAOFactory;
import lk.ijse.carepoint.dao.custom.CustomerDAO;
import lk.ijse.carepoint.dao.custom.MedicineDAO;
import lk.ijse.carepoint.dao.custom.OrderDAO;
import lk.ijse.carepoint.dao.custom.SupplierOrderDAO;
import lk.ijse.carepoint.db.DBConnection;
import lk.ijse.carepoint.dto.PlaceOrderDTO;
import lk.ijse.carepoint.dto.SupplierOrderDTO;
import lk.ijse.carepoint.entity.Order;
import lk.ijse.carepoint.entity.PlaceOrder;
import lk.ijse.carepoint.entity.SupplierOrder;
import lk.ijse.carepoint.model.MedicineModel;
//import lk.ijse.carepoint.model.OrderModel;

import java.sql.SQLException;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    private OrderDAO orderDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);
    private MedicineDAO medicineDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.MEDICINE);
    private SupplierOrderDAO supplierOrderDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPORDER);

    @Override
    public boolean PlaceOrder(PlaceOrderDTO placeOrder) throws SQLException, ClassNotFoundException {
        try{
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean add = orderDAO.add(new Order(placeOrder.getOrder_id(), placeOrder.getOrder_amount(),
                    placeOrder.getOrder_date(), placeOrder.getCustomer_id()));
            if (add){
                System.out.println("Done1");
                boolean saveOrderDetail1 = orderDAO.saveOrderDetail(placeOrder.getOrderdetail(),
                        new PlaceOrder(placeOrder.getOrder_id(), placeOrder.getOrder_amount(), placeOrder.getOrder_date(), placeOrder.getCustomer_id(), placeOrder.getOrderdetail()));
                if (saveOrderDetail1){
                    System.out.println("Done2");
                    boolean updateQty = medicineDAO.updateQty(placeOrder.getOrderdetail());
                    if (updateQty){
                        System.out.println("done3");
                        DBConnection.getInstance().getConnection().commit();
                        return true;
                    }
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        }finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }

    @Override
    public boolean PlaceLoad(SupplierOrderDTO sod) throws SQLException, ClassNotFoundException {
        try{
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean add = supplierOrderDAO.add(new SupplierOrder(sod.getSu_op_id(), sod.getDate(), sod.getAmount(),
                    sod.getSupid(), sod.getDetails()));
            if (add){
                System.out.println("Done1");
                boolean saveOrderDetail = supplierOrderDAO.saveOrderDetail(sod.getDetails(), new SupplierOrder(sod.getSu_op_id(), sod.getDate(),
                        sod.getAmount(), sod.getSupid(), sod.getDetails()));
                if (saveOrderDetail){
                    System.out.println("Done2");
                    boolean updateQty = medicineDAO.updateLoadQty(sod.getDetails());
                    if (updateQty){
                        System.out.println("done3");
                        DBConnection.getInstance().getConnection().commit();
                        return true;
                    }
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        }finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }

    @Override
    public String generateNextOrderId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
