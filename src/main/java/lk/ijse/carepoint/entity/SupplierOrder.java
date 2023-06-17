package lk.ijse.carepoint.entity;

import lombok.*;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class SupplierOrder implements SuperEntity {
    private String su_op_id;
    private String date;
    private double amount;
    private String supid;
    private ArrayList<Detail> details = new ArrayList<>();
}
