package com.alex.consent.domain.transformer;

import com.alex.consent.domain.dto.ConsentDTO;
import com.alex.consent.domain.dto.ExtendedConsentDTO;
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

    public static Consent fromExtendedDtoToEntity(ExtendedConsentDTO extendedConsentDTO) {
        Consent consent = new Consent();
        consent.setType(extendedConsentDTO.getType());
        consent.setChannelName(extendedConsentDTO.getChannelName());
        consent.setConsent(extendedConsentDTO.isConsent());
        return consent;
    }
}
