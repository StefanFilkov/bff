package com.tinqinacademy.bff.api.operations.hotel.models.inputs;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GuestInput {
    @Size(max = 12, message = "Invalid String")
    private String firstName;
    @Size(max = 12, message = "Invalid String")
    private String lastName;

    @NotNull
    private LocalDate cardIssueDate;
    @NotNull
    private LocalDate cardValidity;

    @Nonnull
    private LocalDate birthDate;

    @Nonnull
    private String cardNumber;
    @NonNull
    private String cardIssueAuthority;

    @Nonnull
    private LocalDate endDate;
    @Nonnull
    private LocalDate startDate;

    @Size(max = 20, message = "Invalid String")
    private String email;
    @Size(max = 10, message = "Invalid String")
    private String phoneN;


}
