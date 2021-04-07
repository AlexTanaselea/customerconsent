package com.alex.consent.controller;

import com.alex.consent.domain.dto.ConsentDTO;
import com.alex.consent.domain.enumeration.ConsentType;
import com.alex.consent.domain.service.ConsentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
public class ConsentController {

    private ConsentService consentService;

    public ConsentController(ConsentService consentService) {
        this.consentService = consentService;
    }

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/consents")
    public List<ConsentDTO> findAll(@RequestParam(name = "consentType") ConsentType consentType,
                                    @RequestParam(defaultValue = "0") @Min(0) @Max(100) Integer from,
                                    @RequestParam(defaultValue = "10") @Min(0) @Max(100) Integer size) {
        return consentService.retrieveAll(consentType, from, size);
    }
}
