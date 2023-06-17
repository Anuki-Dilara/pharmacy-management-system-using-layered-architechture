package lk.ijse.carepoint.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class MedicineDTO {
    private String medicine_id;
    private String medicine_name;
    private String description;
    private double unit_price;
    private int quantity;
    private String supplierid;

    public MedicineDTO(String medicine_id, int customerOrder_quantity) {
        this.medicine_id = medicine_id;
        this.quantity = customerOrder_quantity;
    }
}
