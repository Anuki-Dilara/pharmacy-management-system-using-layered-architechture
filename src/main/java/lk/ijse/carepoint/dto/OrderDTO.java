package lk.ijse.carepoint.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class OrderDTO {
    private String order_id;
    private double order_amount;
    private String order_date;
    private String customer_id;
}
