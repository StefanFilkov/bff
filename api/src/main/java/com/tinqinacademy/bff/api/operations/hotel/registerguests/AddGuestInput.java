package com.tinqinacademy.bff.api.operations.hotel.registerguests;

import com.tinqinacademy.bff.api.operations.hotel.models.inputs.GuestInput;
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
public class AddGuestInput implements OperationInput {
    List<@Valid GuestInput> guests;

}
