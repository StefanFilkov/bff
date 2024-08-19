package com.tinqinacademy.bff.api.operations.hotel.updateroom;


import com.tinqinacademy.bff.api.base.OperationOutput;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UpdateRoomOutputBFF implements OperationOutput {

    private String id;

    private Integer floor;

    private List<String> bedSize;

    private String bathroomType;

    private String roomN;

    private BigDecimal price;
}
