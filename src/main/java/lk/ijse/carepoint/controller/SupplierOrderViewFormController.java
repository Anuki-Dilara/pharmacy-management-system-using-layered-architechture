package lk.ijse.carepoint.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.carepoint.bo.BOFactory;
import lk.ijse.carepoint.bo.custom.MedicineBO;
import lk.ijse.carepoint.bo.custom.PlaceOrderBO;
import lk.ijse.carepoint.bo.custom.SupplierBO;
import lk.ijse.carepoint.bo.custom.SupplierOrderBO;
import lk.ijse.carepoint.dto.MedicineDTO;
import lk.ijse.carepoint.dto.SupplierDTO;
import lk.ijse.carepoint.dto.SupplierOrderDTO;
import lk.ijse.carepoint.entity.*;
import lk.ijse.carepoint.dto.tm.SupplierOrderTM;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SupplierOrderViewFormController implements Initializable {
    @FXML
    private Label lblSuppliyId;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblSupplyOrderDate;

    @FXML
    private Label lblSupplierName;

    @FXML
    private Label lblMedicineName;

    @FXML
    private ComboBox<String> cmbSupplierId;

    @FXML
    private ComboBox<String > cmbMedicineId;

    @FXML
    private Label lblQuantityOnHand;

    @FXML
    private TextField txtQuantity;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Label lblAmount;

    @FXML
    private Button btnAddToStock;

    @FXML
    private JFXButton btnPlaceStock;

    @FXML
    private TableView<SupplierOrderTM> tblSupplierOrder;

    @FXML
    private TableColumn<?, ?> colSupplierOrderMedicineId;

    @FXML
    private TableColumn<?, ?> colSupplierOrderMedicineQuantity;

    @FXML
    private TableColumn<?, ?> colSupplierOrderMedicineAmount;

    private  MedicineDTO medicine ;
    Optional<Medicine> med;
    private PlaceOrderBO placeOrderBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.PLACEORER);
    private SupplierBO supplierBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLIER);
    private MedicineBO medicineBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MEDICINE);
    private SupplierOrderBO supplierOrderBO= BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPORDER);

    private ObservableList<SupplierOrderTM> obList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateNextOrderId();
        generateOrderDate();
        loadSupplierIds();
        loadMedicineIds();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colSupplierOrderMedicineId.setCellValueFactory(new PropertyValueFactory<>("medicine_id"));
        colSupplierOrderMedicineQuantity.setCellValueFactory(new PropertyValueFactory<>("supplierOrder_quantity"));
        colSupplierOrderMedicineAmount.setCellValueFactory(new PropertyValueFactory<>("supplierOrder_amount"));
    }

    private void loadSupplierIds() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> supplierids = supplierBO.getIDs();

            for (String supplier_id: supplierids) {
                obList.add(supplier_id);
            }
            cmbSupplierId.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void generateOrderDate() {
        lblSupplyOrderDate.setText(new SimpleDateFormat("20YY-MM-dd").format(new Date()));
    }

    private void loadMedicineIds() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> medicineids = medicineBO.getMedicineIDs();

            for (String medicine_id : medicineids) {
                obList.add(medicine_id);
            }
            cmbMedicineId.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void generateNextOrderId() {
        try {
            String nextid = supplierOrderBO.generateNewLoadID();
            lblSuppliyId.setText(nextid);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Something Happened!").show();
        }

    }


    @FXML
    void btnAddToStockOnAction(ActionEvent event) {
        String medId = cmbMedicineId.getSelectionModel().getSelectedItem().toString();
        int qty = Integer.parseInt(txtQuantity.getText());
        double price = Double.parseDouble(lblUnitPrice.getText());
        Button btn = new Button("Delete");

        ObservableList items = tblSupplierOrder.getItems();
        if (!obList.isEmpty()) {
            L1:
            /* check same item has been in table. If so, update that row instead of adding new row to the table */
            for (int i = 0; i < items.size(); i++) {
                if (colSupplierOrderMedicineId.getCellData(i).equals(medId)) {
                    qty += (int) colSupplierOrderMedicineQuantity.getCellData(i);
                    price = med.get().getUnit_price() * qty;

                    obList.get(i).setSupplierOrder_quantity(qty);
                    obList.get(i).setSupplierOrder_amount(price);
                    tblSupplierOrder.refresh();
                    return;
                }
            }
        }
        obList.add(new SupplierOrderTM(medId,qty,price));
        tblSupplierOrder.setItems(obList);
        calculateNetTotal();
    }

    private void calculateNetTotal() {
        double total = 0.0;
        for (SupplierOrderTM supplierOrderTM : obList){
            total+=supplierOrderTM.getSupplierOrder_amount();
        }
        lblTotal.setText(String.valueOf(total));
    }

    public void cmbSupplierIdOnAction(ActionEvent actionEvent) {
        String supplier_id = String.valueOf(cmbSupplierId.getSelectionModel().getSelectedItem());
        try {
            SupplierDTO supplier = supplierBO.searchSupplier(supplier_id);
            System.out.println(supplier);
            lblSupplierName.setText(supplier.getSupplier_name());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
        loadMedicineIds();
    }

    public void cmbMedicineIdOnAction(ActionEvent actionEvent) {
        String medicine_id = cmbMedicineId.getSelectionModel().getSelectedItem().toString();
        System.out.println(medicine_id);
        try {
            medicine = medicineBO.searchMedicine(medicine_id);
            lblMedicineName.setText(medicine.getMedicine_name());
            lblQuantityOnHand.setText(String.valueOf(medicine.getQuantity()));
            lblUnitPrice.setText(String.valueOf(medicine.getUnit_price()));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    @FXML
    void txtQuantityOnAction(ActionEvent event) {
      int qty = Integer.parseInt(txtQuantity.getText());
      double price = medicine.getUnit_price() * qty;
      lblAmount.setText(String.valueOf(price));
    }

    @FXML
    void btnPlaceStockOnAction(ActionEvent event) {
        String so_id = lblSuppliyId.getText();
        String date = lblSupplyOrderDate.getText();
        String supid = cmbSupplierId.getSelectionModel().getSelectedItem().toString();
        double total = Double.parseDouble(lblAmount.getText());
        ArrayList<Detail> details = new ArrayList<>();
        /* load all cart items' to orderDetails arrayList */
        for (int i = 0; i < tblSupplierOrder.getItems().size(); i++) {
            /* get each row details to (PlaceOrderTm)tm in each time and add them to the orderDetails */
            SupplierOrderTM detailTM = obList.get(i);
            details.add(new Detail(so_id,detailTM.getMedicine_id(),detailTM.getSupplierOrder_quantity(),detailTM.getSupplierOrder_amount()));
        }
        SupplierOrder s1 = new SupplierOrder(so_id,date,total,supid,details);
        System.out.println(s1);

        try {
            boolean placeLoad = placeOrderBO.PlaceLoad(new SupplierOrderDTO(so_id, date, total, supid, details));
            if (placeLoad){
                new Alert(Alert.AlertType.CONFIRMATION,"Load is Added!",ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.ERROR,"CAnnot Added!",ButtonType.CLOSE).show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
