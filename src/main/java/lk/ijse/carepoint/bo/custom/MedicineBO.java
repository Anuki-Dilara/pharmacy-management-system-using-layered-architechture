package lk.ijse.carepoint.bo.custom;

import lk.ijse.carepoint.bo.SuperBO;
//import lk.ijse.carepoint.dto.EmployeeDTO;
import lk.ijse.carepoint.dto.MedicineDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MedicineBO extends SuperBO {
    public ArrayList<MedicineDTO> getAllMedicine() throws SQLException, ClassNotFoundException;

    public boolean addMedicine(MedicineDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean updateMedicine(MedicineDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean existMedicine(String id) throws SQLException, ClassNotFoundException;

    public boolean deleteMedicine(String id) throws SQLException, ClassNotFoundException;

    public String generateNewMedicineID() throws SQLException, ClassNotFoundException;

    public MedicineDTO searchMedicine(String id) throws SQLException, ClassNotFoundException;

    public ArrayList<String> getMedicineIDs() throws SQLException, ClassNotFoundException;

}
