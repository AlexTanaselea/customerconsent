package com.alex.consent.controller;

import com.alex.consent.domain.dto.ConsentDTO;
import com.alex.consent.domain.dto.ExtendedConsentDTO;
import com.alex.consent.domain.dto.UserConsentDTO;
import com.alex.consent.domain.enumeration.ConsentType;
import com.alex.consent.domain.service.ConsentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("api")
public class ConsentController {

    private final ConsentService consentService;

    public ConsentController(ConsentService consentService) {
        this.consentService = consentService;
    }

    @Operation(summary = "Welcome",
            description = "Kind greetings from Spring team.")
    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @Operation(summary = "Add consent",
            description = "This can only be done by providing an existing username and having valid content.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Customer's consent was saved."),
                    @ApiResponse(responseCode = "400", description = "Client's input is unsupported."),
                    @ApiResponse(responseCode = "500", description = "Internal server error.")})
    @PostMapping(value = "/consents", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody ExtendedConsentDTO extendedConsentDTO) {
        consentService.create(extendedConsentDTO);
    }

    @Operation(summary = "Retrieve customers's consents.",
            description = "This can only be done by providing a valid customer id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Customer's consents were retrieved."),
                    @ApiResponse(responseCode = "400", description = "Invalid/missing customer id."),
                    @ApiResponse(responseCode = "500", description = "Internal server error.")})
    @GetMapping(value = "/consents/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserConsentDTO retrieveCustomerConsent(@PathVariable @NotNull Long customerId) {
        return consentService.retrieveCustomerConsent(customerId);
    }

    @Operation(summary = "Retrieve customers's consents.",
            description = "This can only be done by providing a valid consent type.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Anonymous consents by type were retrieved."),
                    @ApiResponse(responseCode = "400", description = "Invalid consent type provided."),
                    @ApiResponse(responseCode = "500", description = "Internal server error.")})
    @GetMapping("/consents")
    public List<ConsentDTO> findAll(@RequestParam(name = "consentType") ConsentType consentType,
                                    @RequestParam(defaultValue = "0") @Min(0) @Max(100) Integer from,
                                    @RequestParam(defaultValue = "10") @Min(0) @Max(100) Integer size) {
        return consentService.retrieveAll(consentType, from, size);
    }
}
