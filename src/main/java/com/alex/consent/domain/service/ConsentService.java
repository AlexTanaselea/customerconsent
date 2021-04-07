package com.alex.consent.domain.service;

import com.alex.consent.domain.dto.ConsentDTO;
import com.alex.consent.domain.dto.ExtendedConsentDTO;
import com.alex.consent.domain.dto.UserConsentDTO;
import com.alex.consent.domain.enumeration.ConsentType;
import com.alex.consent.domain.repository.jpa.ConsentJpaRepository;
import com.alex.consent.domain.repository.jpa.CustomerJpaRepository;
import com.alex.consent.domain.repository.jpa.entity.Consent;
import com.alex.consent.domain.service.util.ConsentGenerator;
import com.alex.consent.domain.transformer.ConsentTransformer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@Service
public class ConsentService {

    private CustomerJpaRepository customerJpaRepository;

    private ConsentJpaRepository consentJpaRepository;

    public ConsentService(CustomerJpaRepository customerJpaRepository, ConsentJpaRepository consentJpaRepository) {
        this.customerJpaRepository = customerJpaRepository;
        this.consentJpaRepository = consentJpaRepository;
    }

    public UserConsentDTO retrieveCustomerConsent(Long customerId) {
        Set<ExtendedConsentDTO> allConsentsByUsername = consentJpaRepository.findAllByCustomerIdAndConsentIsTrue(customerId);
        Set<ConsentDTO> allConsents = ConsentGenerator.generateConsent();
        this.merge(allConsentsByUsername, allConsents);
        return new UserConsentDTO(customerId, allConsents);
    }

    void merge(Collection<? extends ConsentDTO> source, Collection<ConsentDTO> destination) {
        destination.stream()
                .filter(destinationElement -> source.stream().anyMatch(this.isTheSameTypeAndChannelName(destinationElement)))
                .forEach(ConsentDTO::doConsent);
    }

    private Predicate<ConsentDTO> isTheSameTypeAndChannelName(ConsentDTO sourceElement) {
        return destinationElement -> destinationElement.getType().equals(sourceElement.getType())
                && destinationElement.getChannelName().equals(sourceElement.getChannelName());
    }

    public List<ConsentDTO> retrieveAll(ConsentType consentType, int from, int size) {
        Page<Consent> consents = consentJpaRepository.findAllByType(consentType, PageRequest.of(from, size));
        return ConsentTransformer.fromEntitiesToDTOs(consents.getContent());
    }
}
