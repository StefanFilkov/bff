package com.tinqinacademy.bff.core.converters;

import com.tinqinacademy.bff.api.operations.hotel.models.inputs.GuestInputBFF;
import com.tinqinacademy.bff.api.operations.hotel.registerguests.AddGuestsOutputBFF;
import com.tinqinacademy.hotel.api.models.inputs.GuestInput;
import com.tinqinacademy.hotel.api.operations.registerguests.AddGuestInput;
import com.tinqinacademy.hotel.api.operations.registerguests.AddGuestsOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Component
public class AddGuestOutputToAddGuestOutputBFF extends LoggedConverter<AddGuestsOutput, AddGuestsOutputBFF>{
    private final ObjectMapperConvertor objectMapperConvertor;

    public AddGuestOutputToAddGuestOutputBFF(ObjectMapperConvertor objectMapperConvertor) {
        super();
        this.objectMapperConvertor = objectMapperConvertor;
    }

    @Override
    protected AddGuestsOutputBFF convertTo(AddGuestsOutput input) {
        return AddGuestsOutputBFF.builder()
                .guests(input.getGuests().stream().map(bbfGuests -> {
                            try {
                                return objectMapperConvertor.convert(bbfGuests, GuestInputBFF.class);
                            } catch (Exception e) {

                                throw new RuntimeException(e);
                            }
                        })
                        .toList())
                .build();
    }
}
