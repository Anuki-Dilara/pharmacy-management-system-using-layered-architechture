package lk.ijse.carepoint.bo.custom.impl;

import lk.ijse.carepoint.bo.custom.MedicineBO;
import lk.ijse.carepoint.dao.CrudUtil;
import lk.ijse.carepoint.dao.DAOFactory;
import lk.ijse.carepoint.dao.custom.EmployeeDAO;
import lk.ijse.carepoint.dao.custom.MedicineDAO;
import lk.ijse.carepoint.dto.EmployeeDTO;
import lk.ijse.carepoint.dto.MedicineDTO;
import lk.ijse.carepoint.dto.SupplierDTO;
import lk.ijse.carepoint.entity.Employee;
import lk.ijse.carepoint.entity.Medicine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicineBOImpl implements MedicineBO {
    private MedicineDAO medicineDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.MEDICINE);

    @Override
    public ArrayList<MedicineDTO> getAllMedicine() throws SQLException, ClassNotFoundException {
        ArrayList<MedicineDTO> allMedicine= new ArrayList<>();
        ArrayList<Medicine> all = medicineDAO.getAll();
        for (Medicine medicine : all) {
            allMedicine.add(new MedicineDTO(medicine.getMedicine_id(),medicine.getMedicine_name(),medicine.getDescription(),medicine.getUnit_price(),medicine.getQuantity(),medicine.getSupplierid()));
        }
        return allMedicine;
    }

    @Override
    public boolean addMedicine(MedicineDTO dto) throws SQLException, ClassNotFoundException {
        return medicineDAO.add(new Medicine(
                dto.getMedicine_id(),
                dto.getMedicine_name(),
                dto.getDescription(),
                dto.getUnit_price(),
                dto.getQuantity(),
                dto.getSupplierid()
        ));
    }

    @Override
    public boolean updateMedicine(MedicineDTO dto) throws SQLException, ClassNotFoundException {
        return medicineDAO.update(new Medicine(
                dto.getMedicine_id(),
                dto.getMedicine_name(),
                dto.getDescription(),
                dto.getUnit_price(),
                dto.getQuantity(),
                dto.getSupplierid()
        ));
    }

    @Override
    public boolean existMedicine(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteMedicine(String id) throws SQLException, ClassNotFoundException {
        return medicineDAO.delete(id);
    }

    @Override
    public String generateNewMedicineID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public MedicineDTO searchMedicine(String id) throws SQLException, ClassNotFoundException {
        Medicine search = medicineDAO.search(id);
        return new MedicineDTO(search.getMedicine_id(), search.getMedicine_name(),search.getDescription(),
                search.getUnit_price(),search.getQuantity(), search.getSupplierid());
    }

    @Override
    public ArrayList<String> getMedicineIDs() throws SQLException, ClassNotFoundException {
        ArrayList<String> ids = new ArrayList<>();

        ArrayList<String> all = medicineDAO.getMedicineIDs();
        for (String id : all) {
            ids.add(id);
        }
        return ids;
    }
}
