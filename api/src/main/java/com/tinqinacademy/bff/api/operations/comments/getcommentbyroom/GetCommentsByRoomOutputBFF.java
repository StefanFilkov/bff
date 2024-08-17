package com.tinqinacademy.bff.api.operations.comments.getcommentbyroom;

import com.tinqinacademy.bff.api.base.OperationOutput;

import com.tinqinacademy.bff.api.operations.comments.models.output.CommentOutputBFF;
import lombok.*;

import java.util.List;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class GetCommentsByRoomOutputBFF implements OperationOutput {
    List<CommentOutputBFF> commentOutputs;

}
