package com.tinqinacademy.bff.api.operations.hotel.models.outputs;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GuestOutputBFF {
    private String firstName;
    private String lastName;
    private String cardNumber;

    private String cardIssueAuthority;
    private LocalDate cardValidity;
    private LocalDate cardIssueDate;

    private LocalDate birthDate;
}
