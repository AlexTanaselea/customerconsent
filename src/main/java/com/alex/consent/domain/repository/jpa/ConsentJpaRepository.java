package com.alex.consent.domain.repository.jpa;

import com.alex.consent.domain.dto.ExtendedConsentDTO;
import com.alex.consent.domain.enumeration.ConsentType;
import com.alex.consent.domain.repository.jpa.entity.Consent;
import com.alex.consent.domain.repository.jpa.entity.key.ConsentKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ConsentJpaRepository extends JpaRepository<Consent, ConsentKey> {

    @Query("SELECT new com.alex.consent.domain.dto.ExtendedConsentDTO(customer.username, consent.type, consent.channelName, consent.consent) " +
            "FROM Consent consent " +
            "LEFT JOIN Customer customer ON consent.customerId = customer.id " +
            "WHERE consent.customerId = :customerId")
    Set<ExtendedConsentDTO> findAllByCustomerIdAndConsentIsTrue(@Param("customerId") Long customerId);

    Page<Consent> findAllByType(ConsentType type, Pageable pageable);
}
