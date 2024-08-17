package com.tinqinacademy.bff.api.operations.hotel.getroombyid;


import com.tinqinacademy.bff.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetRoomByIdInputBFF implements OperationInput {

    @UUID
    @NotBlank
    private String id;
}
