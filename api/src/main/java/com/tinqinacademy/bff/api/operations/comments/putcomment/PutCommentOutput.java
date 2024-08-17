package com.tinqinacademy.bff.api.operations.comments.putcomment;

import com.tinqinacademy.bff.api.base.OperationOutput;
import lombok.*;


@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class PutCommentOutput implements OperationOutput {
    private String id;
}
