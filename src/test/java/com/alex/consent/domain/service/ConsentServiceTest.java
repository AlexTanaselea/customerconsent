package com.alex.consent.domain.service;

import com.alex.consent.domain.dto.ConsentDTO;
import com.alex.consent.domain.dto.ExtendedConsentDTO;
import com.alex.consent.domain.dto.UserConsentDTO;
import com.alex.consent.domain.enumeration.ChannelName;
import com.alex.consent.domain.enumeration.ConsentType;
import com.alex.consent.domain.repository.jpa.ConsentJpaRepository;
import com.alex.consent.domain.service.util.ConsentGenerator;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class ConsentServiceTest {

    @Mock
    private ConsentJpaRepository consentJpaRepository;

    @InjectMocks
    private ConsentService consentService;

    @Test
    public void mergeShouldUpdateTheDestinationList() {

        List<ConsentDTO> allConsents = this.generateConsents();

        List<ExtendedConsentDTO> existingConsent = this.getExistingConsent();

        consentService.merge(existingConsent, allConsents);

        List<ConsentDTO> expectedConsents = this.generateExpectedConsents();

        assertThat(allConsents, is(expectedConsents));
    }

    private List<ExtendedConsentDTO> getExistingConsent() {
        return List.of(new ExtendedConsentDTO("1", ConsentType.ESSENTIALS, ChannelName.EMAIL, true));
    }

    private List<ConsentDTO> generateExpectedConsents() {
        return List.of(new ConsentDTO(ConsentType.ESSENTIALS, ChannelName.EMAIL, true),
                new ConsentDTO(ConsentType.ESSENTIALS, ChannelName.PHONE));
    }

    private List<ConsentDTO> generateConsents() {
        return List.of(new ConsentDTO(ConsentType.ESSENTIALS, ChannelName.EMAIL),
                new ConsentDTO(ConsentType.ESSENTIALS, ChannelName.PHONE));
    }

    @Test
    @Ignore
    public void retrieveUserConsentShouldFetchAllDataForASpecificUser() {
        Long userId = 1L;
        doReturn(new HashSet<>(this.getExistingConsent())).when(consentJpaRepository).findAllByCustomerIdAndConsentIsTrue(userId);

        UserConsentDTO actual = consentService.retrieveCustomerConsent(userId);

        assertThat(actual, is(this.generateExpectedUserConsent(userId)));
    }

    private UserConsentDTO generateExpectedUserConsent(Long userId) {
        Set<ConsentDTO> consents = ConsentGenerator.generateConsent();
        consents.stream().filter(matchExistingConsent()).findFirst().get().setConsent(true);
        return new UserConsentDTO(userId, consents);
    }

    private Predicate<ConsentDTO> matchExistingConsent() {
        return consentDTO -> ConsentType.ESSENTIALS.equals(consentDTO.getType()) && ChannelName.EMAIL.equals(consentDTO.getChannelName());
    }

    @Test
    public void addUserPreferenceShouldSaveNewConsent() {
        fail();
    }

}
