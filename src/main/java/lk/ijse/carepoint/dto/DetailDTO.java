package lk.ijse.carepoint.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class DetailDTO {
    private String su_or_id;
    private String Med_id;
    private  int qty;
    private double amount;
}
