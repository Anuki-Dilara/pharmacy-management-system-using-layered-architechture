package lk.ijse.carepoint.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Customer implements SuperEntity {
    private String Customer_id;
    private String customer_name;
    private String customer_address;
    private int customer_age;
    private String phone_number;
}
