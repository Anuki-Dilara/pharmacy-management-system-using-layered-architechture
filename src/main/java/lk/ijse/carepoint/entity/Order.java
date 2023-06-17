package lk.ijse.carepoint.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Order implements SuperEntity {
    private String order_id;
    private double order_amount;
    private String order_date;
    private String customer_id;
}
