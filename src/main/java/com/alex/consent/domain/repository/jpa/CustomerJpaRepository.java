package com.alex.consent.domain.repository.jpa;


import com.alex.consent.domain.repository.jpa.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByUsername(String username);
}
