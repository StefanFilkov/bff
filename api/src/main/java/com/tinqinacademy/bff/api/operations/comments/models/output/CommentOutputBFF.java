package com.tinqinacademy.bff.api.operations.comments.models.output;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class CommentOutputBFF {
    private String id;
    private String content;
    private String authorFirstName;
    private String authorLastName;
    private LocalDate createdOn;
    private LocalDate updatedOn;
    private String lastEditedBy;
    private String roomId;

}
