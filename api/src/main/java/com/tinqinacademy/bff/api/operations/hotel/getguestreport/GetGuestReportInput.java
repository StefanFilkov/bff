package com.tinqinacademy.bff.api.operations.hotel.getguestreport;


import com.tinqinacademy.bff.api.base.OperationInput;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetGuestReportInput implements OperationInput {
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;

    @Size(max = 15, message = "invalid name")
    private String firstName;
    @Size(max = 15, message = "invalid name")
    private String lastName;

    @Size(max = 15, message = "string must be >0 and <16")
    private String cardIssueDate;
    @Size(max = 15, message = "string must be >0 and <16")
    private String cardValidityDate;

    @Size(max = 15, message = "string must be >0 and <16")
    private String cardIdN;
    @Size(max = 15, message = "string must be >0 and <16")
    private String cardIssueAuthority;

    @Size(max = 15, message = "string must be >0 and <16")
    private String birthdate;
    @Size(max = 15, message = "string must be >0 and <16")
    private String phoneN;

    @Size(max = 15, message = "string must be >0 and <16")
    private String roomN;


}
