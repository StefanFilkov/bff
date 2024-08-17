package com.tinqinacademy.bff.api.operations.hotel.editroom;

import com.tinqinacademy.hotel.api.base.OperationOutput;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

@Builder
@ToString
public class EditRoomOutputBFF implements OperationOutput {
    private final String id;
}
