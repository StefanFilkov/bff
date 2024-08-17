package com.tinqinacademy.bff.api.operations.comments.updatecomment;

import com.tinqinacademy.bff.api.base.OperationOutput;
import lombok.*;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class UpdateCommentOutputBFF implements OperationOutput {
    private String id;
}
