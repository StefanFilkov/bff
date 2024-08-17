package com.tinqinacademy.bff.api.operations.hotel.editroom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.hotel.api.base.OperationInput;
import com.tinqinacademy.hotel.api.validation.bathroomtype.BathroomTypeValidation;
import com.tinqinacademy.hotel.api.validation.bedsize.BedSizeValidation;
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
public class EditRoomInputBFF implements OperationInput {
    @JsonIgnore
    private String id;

    private List< @BedSizeValidation String> bedSizes;


    @Min(value = 0, message = "cannot be less than 0")
    @Max(value = 100, message = "cannot be more than 100")
    private Integer floor;


    @BathroomTypeValidation
    private String bathroomType;

    @Size(max = 12, message = "invalid room Number")
    private String roomN;

    @Digits(integer = 4, fraction = 2, message = "invalid number")
    private BigDecimal price;
}
