package com.alex.consent.domain.dto;

import java.util.Objects;
import java.util.Set;

public class UserConsentDTO {

    private Long customerId;
    private Set<ConsentDTO> consentDTOS;

    public UserConsentDTO(Long customerId, Set<ConsentDTO> consentDTOS) {
        this.customerId = customerId;
        this.consentDTOS = consentDTOS;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Set<ConsentDTO> getConsentDTOS() {
        return consentDTOS;
    }

    public void setConsentDTOS(Set<ConsentDTO> consentDTOS) {
        this.consentDTOS = consentDTOS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserConsentDTO that = (UserConsentDTO) o;
        return Objects.equals(customerId, that.customerId) &&
                Objects.equals(consentDTOS, that.consentDTOS);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, consentDTOS);
    }

    @Override
    public String toString() {
        return "UserConsentDTO{" +
                "customerId='" + customerId + '\'' +
                ", consentDTOS=" + consentDTOS +
                '}';
    }
}
