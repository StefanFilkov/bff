package com.tinqinacademy.bff.api.operations.comments.postcomment;

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
public class PostCommentInputBFF implements OperationInput {

    @JsonIgnore
    private String roomId;

    @Size(min = 0, max = 12, message = "invalid string")
    private String firstName;

    @Size(min = 0, max = 12, message = "invalid string")
    private String lastName;

    @Size(min = 0, max = 12, message = "invalid string")
    private String content;
}
