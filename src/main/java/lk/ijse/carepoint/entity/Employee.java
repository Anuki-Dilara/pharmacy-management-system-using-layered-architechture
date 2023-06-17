package lk.ijse.carepoint.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class Employee implements SuperEntity{
    private String employee_id;
    private String employee_name;
    private String employee_address;
    private String employee_contactNumber;
    private double employee_salary;
    private int employee_age;



}
