package lk.ijse.carepoint.bo;

import lk.ijse.carepoint.bo.custom.impl.*;
import lk.ijse.carepoint.dao.custom.impl.EmployeeDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {
    }
    public static BOFactory getInstance() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        //create object types
        CUSTOMER,SUPPLIER,EMPLOYEE,MEDICINE,ORDER,SUPORDER,PLACEORER,LOGIN
    }


    //Factory Design pattern
    public <T> T getBO(BOTypes type){
        switch (type) {
            case CUSTOMER:
                return (T) new CustomerBOImpl();
            case SUPPLIER:
                return (T) new SupplierBOImpl();
            case EMPLOYEE:
                return (T) new EmployeeBOImpl();
            case MEDICINE:
                return (T) new MedicineBOImpl();
            case ORDER:
                return (T) new OrderBOImpl();
            case SUPORDER:
                return (T) new SupplierOrderBOImpl();
            case PLACEORER:
                return (T) new PlaceOrderBOImpl();
            case LOGIN:
                return (T) new LoginBOImpl();
            /*case QUERY_DAO:
                return (T) new QueryDAOImpl();*/
            default:
                return null;
        }
    }
}
