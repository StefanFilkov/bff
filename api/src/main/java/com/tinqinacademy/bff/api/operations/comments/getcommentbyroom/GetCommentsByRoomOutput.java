package com.tinqinacademy.bff.api.operations.comments.getcommentbyroom;

import com.tinqinacademy.bff.api.base.OperationOutput;

import com.tinqinacademy.bff.api.operations.comments.models.output.CommentOutput;
import lombok.*;

import java.util.List;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class GetCommentsByRoomOutput implements OperationOutput {
    List<CommentOutput> commentOutputs;

}
