package com.tinqinacademy.bff.api.operations.hotel.registerguests;


import com.tinqinacademy.bff.api.operations.hotel.models.inputs.GuestInputBFF;
import com.tinqinacademy.bff.api.base.OperationOutput;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AddGuestsOutputBFF implements OperationOutput {
    List<GuestInputBFF> guests;
}
