package com.tinqinacademy.bff.api.operations.comments.getcommentbyroom;

import com.tinqinacademy.bff.api.base.OperationInput;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GetCommentsByRoomInput implements OperationInput {
    @UUID
    @NotNull
    private String id;
}
