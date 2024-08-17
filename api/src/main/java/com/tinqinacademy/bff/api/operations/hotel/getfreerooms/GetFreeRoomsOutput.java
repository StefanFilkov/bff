package com.tinqinacademy.bff.api.operations.hotel.getfreerooms;

import com.tinqinacademy.bff.api.base.OperationOutput;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetFreeRoomsOutput implements OperationOutput {
    private List<String> ids;
}
