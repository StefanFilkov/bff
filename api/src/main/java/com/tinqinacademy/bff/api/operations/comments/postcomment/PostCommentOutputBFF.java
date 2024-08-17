package com.tinqinacademy.bff.api.operations.comments.postcomment;

import com.tinqinacademy.bff.api.base.OperationOutput;
import lombok.*;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class PostCommentOutputBFF implements OperationOutput {
    private String id;
    private String firstName;
    private String lastName;
    private String content;
}
