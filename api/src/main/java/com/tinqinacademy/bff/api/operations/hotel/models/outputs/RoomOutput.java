package com.tinqinacademy.bff.api.operations.hotel.models.outputs;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RoomOutput {
    private UUID id;
    private String number;
    private Integer floor;
    private BigDecimal price;
    private List<String> beds;
    private String bathroomTypes;

}
