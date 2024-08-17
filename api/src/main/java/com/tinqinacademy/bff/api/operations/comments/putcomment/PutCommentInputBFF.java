package com.tinqinacademy.bff.api.operations.comments.putcomment;

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
public class PutCommentInputBFF implements OperationInput {

    @JsonIgnore
    private String id;
    @Size(min=0,max = 12, message = "String invalid")
    private String roomId;
    @Size(max = 15, message = "string must be >0 and <16")
    private String firstName;
    @Size(max = 15, message = "string must be >0 and <16")
    private String lastName;
    @Size(max = 15, message = "string must be >0 and <16")
    private String content;
}
