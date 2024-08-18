package com.tinqinacademy.bff.core.converters;

import com.tinqinacademy.bff.api.operations.hotel.registerguests.AddGuestsInputBFF;
import com.tinqinacademy.hotel.api.models.inputs.GuestInput;
import com.tinqinacademy.hotel.api.operations.registerguests.AddGuestInput;
import org.springframework.stereotype.Component;

@Component
public class AddGuestInputBFFToAddGuestInput extends LoggedConverter<AddGuestsInputBFF, AddGuestInput> {
    private final ObjectMapperConvertor objectMapperConvertor;

    public AddGuestInputBFFToAddGuestInput(ObjectMapperConvertor objectMapperConvertor) {
        super();
        this.objectMapperConvertor = objectMapperConvertor;
    }

    @Override
    protected AddGuestInput convertTo(AddGuestsInputBFF input) {
        return AddGuestInput.builder()
                .guests(input
                        .getGuests()
                        .stream()
                        .map(bbfGuests -> {
                            try {
                                return objectMapperConvertor.convert(bbfGuests, GuestInput.class);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .toList())
                .build();
    }
}