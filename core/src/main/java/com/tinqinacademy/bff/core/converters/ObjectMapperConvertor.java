package com.tinqinacademy.bff.core.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ObjectMapperConvertor {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public <I,O> O convert(I inputClass, Class<O> targetClass) throws Exception {
        log.info("Converting (obj convertor) {} to {}", inputClass.getClass().getSimpleName(), targetClass.getSimpleName());

        String json = objectMapper.writeValueAsString(inputClass);
        O result = objectMapper.readValue(json, targetClass);

        log.info("Conversion input and result (obj convertor) {} to {}", inputClass, result);
        return result;
    }
}
