package lk.ijse.carepoint.entity;

import lombok.*;

import java.util.ArrayList;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class PlaceOrder {
    private String order_id;
    private double order_amount;
    private String order_date;
    private String customer_id;
    private ArrayList<Cart> orderdetail = new ArrayList<>();
}
