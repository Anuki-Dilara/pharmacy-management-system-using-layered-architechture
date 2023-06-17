package lk.ijse.carepoint.dao;

import lk.ijse.carepoint.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {
    }
    public static DAOFactory getInstance() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        //create object types
        CUSTOMER,SUPPLIER,EMPLOYEE,MEDICINE,ORDER,SUPORDER,LOGIN
    }


    //Factory Design pattern
    public <T> T getDAO(DAOTypes type){
        switch (type) {
            case CUSTOMER:
                return (T) new CustomerDAOImpl();
            case SUPPLIER:
                return (T) new SupplierDAOImpl();
            case EMPLOYEE:
                return (T) new EmployeeDAOImpl();
            case MEDICINE:
                return (T) new MedicineDAOImpl();
            case ORDER:
                return (T) new OrderDAOImpl();
            case SUPORDER:
                return (T) new SupplierOrderDAOImpl();
            case LOGIN:
                return (T) new LoginDAOImpl();
            /*case QUERY_DAO:
                return (T) new QueryDAOImpl();*/
            default:
                return null;
        }
    }
}
