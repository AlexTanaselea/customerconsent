package com.alex.consent.domain.repository.jpa;


import com.alex.consent.domain.repository.jpa.entity.Consent;
import com.alex.consent.domain.repository.jpa.entity.key.ConsentKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJpaRepository extends JpaRepository<Consent, ConsentKey> {
}
