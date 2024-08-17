package com.tinqinacademy.bff.api.operations.comments.updatecomment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.bff.api.base.OperationInput;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class UpdateCommentInput implements OperationInput {
    @JsonIgnore
    private String id;
    @Size(max = 15, message = "string must be >0 and <16")
    private String content;
}

