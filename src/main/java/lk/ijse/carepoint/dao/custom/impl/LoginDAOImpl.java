package lk.ijse.carepoint.dao.custom.impl;

import lk.ijse.carepoint.dao.CrudUtil;
import lk.ijse.carepoint.dao.custom.LoginDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImpl implements LoginDAO {
    @Override
    public boolean userCheckedInDB(String username, String password) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM user WHERE username= ? AND password=?";
        ResultSet resultSet = CrudUtil.execute(sql, username, password);
        if(resultSet.next()){
            return true;
        }
        return false;
    }
}
