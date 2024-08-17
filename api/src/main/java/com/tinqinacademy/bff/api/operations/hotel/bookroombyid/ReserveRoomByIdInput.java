package com.tinqinacademy.bff.api.operations.hotel.bookroombyid;

import com.tinqinacademy.bff.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReserveRoomByIdInput implements OperationInput {
    @UUID
    @NotBlank
    private String roomId;

    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;

    @Size(max = 15, message = "Name must be >0 and <16")
    private String firstName;
    @Size(max = 15, message = "Name must be >0 and <16")
    private String lastName;

    @Pattern(regexp="(^$|[0-9]{10})")
    private String phone;
}
