package com.tinqinacademy.bff.api.operations.hotel.getguestreport;


import com.tinqinacademy.bff.api.base.OperationOutput;
import com.tinqinacademy.bff.api.operations.hotel.models.outputs.GuestOutputBFF;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetGuestReportOutputBFF implements OperationOutput {

    private List<GuestOutputBFF> data;


}
