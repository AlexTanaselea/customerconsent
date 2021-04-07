package com.alex.consent.domain.transformer;

import com.alex.consent.domain.dto.ConsentDTO;
import com.alex.consent.domain.repository.jpa.entity.Consent;

import java.util.List;
import java.util.stream.Collectors;

public final class ConsentTransformer {

    private ConsentTransformer() {
    }

    public static List<ConsentDTO> fromEntitiesToDTOs(List<Consent> consents) {
        return consents.stream()
                .map(ConsentTransformer::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    private static ConsentDTO fromEntityToDTO(Consent consent) {
        return new ConsentDTO(consent.getType(), consent.getChannelName(), consent.isConsent());
    }
}
