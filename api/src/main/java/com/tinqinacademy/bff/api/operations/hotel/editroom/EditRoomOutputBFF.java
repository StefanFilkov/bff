package com.tinqinacademy.bff.api.operations.hotel.editroom;

import com.tinqinacademy.hotel.api.base.OperationOutput;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EditRoomOutputBFF implements OperationOutput, com.tinqinacademy.bff.api.base.OperationOutput {
    private String id;
}
