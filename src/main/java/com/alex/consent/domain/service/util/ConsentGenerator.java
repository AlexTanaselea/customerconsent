package com.alex.consent.domain.service.util;

import com.alex.consent.domain.dto.ConsentDTO;
import com.alex.consent.domain.enumeration.ChannelName;
import com.alex.consent.domain.enumeration.ConsentType;
import org.springframework.data.util.Pair;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ConsentGenerator {

    private static Set<Pair<ConsentType, ChannelName>> CONSENT_PAIRS = new HashSet<>();

    static {
        Stream.of(ConsentType.values()).forEach( consentType -> {
            Stream.of(ChannelName.values()).forEach(channelName -> CONSENT_PAIRS.add(Pair.of(consentType, channelName)));
        });
    }

    public static Set<ConsentDTO> generateConsent() {
        return CONSENT_PAIRS.stream()
                .map(pair -> new ConsentDTO(pair.getFirst(), pair.getSecond()))
                .collect(Collectors.toSet());
    }

    private ConsentGenerator() {
    }
}
