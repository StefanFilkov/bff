package com.tinqinacademy.bff.api.operations.comments.deletecomment;

import com.tinqinacademy.bff.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class DeleteCommentInputBFF implements OperationInput {

    @NotBlank
    @UUID
    private String commentId;
}
