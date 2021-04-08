package com.alex.consent.domain.dto;

import com.alex.consent.domain.enumeration.ChannelName;
import com.alex.consent.domain.enumeration.ConsentType;

public class ExtendedConsentDTO extends ConsentDTO {

    private String username;

    public ExtendedConsentDTO() {
    }

    public ExtendedConsentDTO(String username, ConsentType type, ChannelName channelName, boolean consent) {
        super(type, channelName, consent);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
