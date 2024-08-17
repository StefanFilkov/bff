package com.tinqinacademy.bff.api.operations.hotel.deleteroom;


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
public class DeleteRoomInputBFF implements OperationInput {
    @UUID
    @NotBlank
    private String id;
}
