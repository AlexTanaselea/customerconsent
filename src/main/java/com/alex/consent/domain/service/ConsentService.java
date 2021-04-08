package com.alex.consent.domain.service;

import com.alex.consent.domain.dto.ConsentDTO;
import com.alex.consent.domain.dto.ExtendedConsentDTO;
import com.alex.consent.domain.dto.UserConsentDTO;
import com.alex.consent.domain.enumeration.ConsentType;
import com.alex.consent.domain.repository.jpa.ConsentJpaRepository;
import com.alex.consent.domain.repository.jpa.CustomerJpaRepository;
import com.alex.consent.domain.repository.jpa.entity.Consent;
import com.alex.consent.domain.repository.jpa.entity.Customer;
import com.alex.consent.domain.service.util.ConsentGenerator;
import com.alex.consent.domain.transformer.ConsentTransformer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

@Service
public class ConsentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsentService.class);

    private final CustomerJpaRepository customerJpaRepository;

    private final ConsentJpaRepository consentJpaRepository;

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
                && destinationElement.getChannelName().equals(sourceElement.getChannelName())
                && destinationElement.isConsent() != sourceElement.isConsent();
    }

    public List<ConsentDTO> retrieveAll(ConsentType consentType, int from, int size) {
        Page<Consent> consents = consentJpaRepository.findAllByType(consentType, PageRequest.of(from, size));
        return ConsentTransformer.fromEntitiesToDTOs(consents.getContent());
    }

    public void create(ExtendedConsentDTO extendedConsentDTO) {
        try {
            consentJpaRepository.save(this.retrieveEntityFromUserInput(extendedConsentDTO));
        } catch (Exception exception) {
            LOGGER.error("Could not process the payload", exception);
            throw new IllegalArgumentException("Unsupported payload.");
        }
    }

    private Consent retrieveEntityFromUserInput(ExtendedConsentDTO extendedConsentDTO) {
        Long customerId = this.findCustomerIdByUsername(extendedConsentDTO.getUsername());
        Consent consent = ConsentTransformer.fromExtendedDtoToEntity(extendedConsentDTO);
        consent.setCustomerId(customerId);
        return consent;
    }

    private Long findCustomerIdByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("Missing username.");
        }
        Optional<Customer> customer = customerJpaRepository.findByUsername(username);
        return customer.map(Customer::getId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Customer %s not found.", username)));
    }
}
