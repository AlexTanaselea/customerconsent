package com.alex.consent.controller.converter;

import com.alex.consent.domain.enumeration.ConsentType;
import org.springframework.core.convert.converter.Converter;

import java.util.Optional;

public class ConsentTypeConverter implements Converter<String, ConsentType> {
    @Override
    public ConsentType convert(String s) {
        return Optional.ofNullable(s)
                .map(type -> ConsentType.valueOf(type.toUpperCase()))
                .orElseThrow(() -> new IllegalArgumentException("Missing consent type."));
    }
}
