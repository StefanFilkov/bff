package com.tinqinacademy.bff.api.operations.hotel.createroom;

import com.tinqinacademy.bff.api.base.OperationInput;
import com.tinqinacademy.bff.api.validation.bathroomtype.BathroomTypeValidation;
import com.tinqinacademy.bff.api.validation.bedsize.BedSizeValidation;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreateRoomInputBFF implements OperationInput {


    private List< @BedSizeValidation String> beds;

    @BathroomTypeValidation
    private String bathroomType;

    @Min(value = 0, message = "cannot be less than 0")
    @Max(value = 100, message = "cannot be more than 100")
    private Integer floor;

    @Size(min = 0, max = 12, message = "invalid string")
    private String roomN;

    @Digits(integer = 4, fraction = 2, message = "invalid number")
    private BigDecimal price;
}
