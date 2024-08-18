package com.tinqinacademy.bff.api.operations.hotel.getfreerooms;


import com.tinqinacademy.bff.api.base.OperationInput;
import com.tinqinacademy.bff.api.validation.bathroomtype.BathroomTypeValidation;
import com.tinqinacademy.bff.api.validation.bedsize.BedSizeValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetFreeRoomsInputBFF implements OperationInput {
    @UUID
    private String id;

    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;

    @NotBlank
    private List<@BedSizeValidation String> bedSizes;

    @BathroomTypeValidation
    private String bathroomTypes;

}
