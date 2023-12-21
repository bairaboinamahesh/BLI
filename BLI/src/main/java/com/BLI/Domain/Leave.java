package com.BLI.Domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String EmployeeName;

    String LeaveType;
    String startDate;
    String endDate;

    String NumOfDays;
    String reason;
    String Acceptance;

}
