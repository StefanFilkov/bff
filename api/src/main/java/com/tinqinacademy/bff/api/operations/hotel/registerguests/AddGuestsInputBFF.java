package com.tinqinacademy.bff.api.operations.hotel.registerguests;

import com.tinqinacademy.bff.api.operations.hotel.models.inputs.GuestInputBFF;
import com.tinqinacademy.bff.api.base.OperationInput;
import jakarta.validation.Valid;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AddGuestsInputBFF implements OperationInput {
    List<GuestInputBFF> guests;

}
