package lk.ijse.carepoint.dto;

import lk.ijse.carepoint.entity.Detail;
import lombok.*;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class SupplierOrderDTO {
    private String su_op_id;
    private String date;
    private double amount;
    private String supid;
    private ArrayList<Detail> details = new ArrayList<>();
}
