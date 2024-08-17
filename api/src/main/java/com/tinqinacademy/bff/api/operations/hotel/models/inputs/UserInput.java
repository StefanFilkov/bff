package com.tinqinacademy.bff.api.operations.hotel.models.inputs;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserInput {
    private UUID id;
    private String Firstname;
    private String lastName;

    private LocalDate birthDate;
    private String email;
    private String phone;
}
