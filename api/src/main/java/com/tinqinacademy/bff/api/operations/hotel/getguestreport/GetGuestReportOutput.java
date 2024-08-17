package com.tinqinacademy.bff.api.operations.hotel.getguestreport;


import com.tinqinacademy.bff.api.base.OperationOutput;
import com.tinqinacademy.bff.api.operations.hotel.models.outputs.GuestOutput;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetGuestReportOutput implements OperationOutput {

    private List<GuestOutput> data;


}
