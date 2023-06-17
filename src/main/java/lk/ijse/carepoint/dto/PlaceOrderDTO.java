package lk.ijse.carepoint.dto;

import lk.ijse.carepoint.entity.Cart;
import lombok.*;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class PlaceOrderDTO {
    private String order_id;
    private double order_amount;
    private String order_date;
    private String customer_id;
    private ArrayList<Cart> orderdetail = new ArrayList<>();
}
