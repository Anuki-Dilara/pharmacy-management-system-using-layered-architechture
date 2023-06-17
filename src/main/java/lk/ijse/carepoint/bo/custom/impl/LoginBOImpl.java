package lk.ijse.carepoint.bo.custom.impl;

import lk.ijse.carepoint.bo.custom.LoginBO;
import lk.ijse.carepoint.dao.DAOFactory;
import lk.ijse.carepoint.dao.custom.LoginDAO;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {
    private LoginDAO loginDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.LOGIN);
    @Override
    public boolean userCheckedInDB(String username, String password) throws SQLException, ClassNotFoundException {
        return loginDAO.userCheckedInDB(username,password);
    }
}
