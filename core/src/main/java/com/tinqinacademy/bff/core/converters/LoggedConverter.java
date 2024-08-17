package com.tinqinacademy.bff.core.converters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public abstract class LoggedConverter<I, O> implements Converter<I, O> {

    @Override
    public O convert(I input) {
        log.info("Converting {} with values input: {}",input.getClass().getSimpleName(),input);

        O result = convertTo(input);

        log.info("Converted {} to {} with result: {}",input.getClass().getSimpleName(), result.getClass().getSimpleName(), result);
        return result;
    }
    protected abstract O convertTo(I input);
}
