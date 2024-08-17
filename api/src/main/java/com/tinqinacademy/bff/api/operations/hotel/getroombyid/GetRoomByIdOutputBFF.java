package com.tinqinacademy.bff.api.operations.hotel.getroombyid;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.tinqinacademy.bff.api.base.OperationOutput;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetRoomByIdOutputBFF implements OperationOutput {
    private String id;
    private String number;
    private Integer floor;
    private BigDecimal price;
    private List<String> beds;
    private String bathroomTypes;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private List<LocalDate> datesOccupied;
}
