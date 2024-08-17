package com.tinqinacademy.bff.api.operations.hotel.registerguests;


import com.tinqinacademy.bff.api.operations.hotel.models.inputs.GuestInput;
import com.tinqinacademy.bff.api.base.OperationOutput;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class AddGuestsOutput implements OperationOutput {
    List<GuestInput> guests;
}
