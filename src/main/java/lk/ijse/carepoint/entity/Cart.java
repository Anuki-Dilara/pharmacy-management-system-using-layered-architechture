package lk.ijse.carepoint.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Cart {
    private String medicine_id;
    private String medicine_name;
    private int customerOrder_quantity;
    private double customerOrder_unitPrice;
    private double customerOrder_amount;
}
