package com.alex.consent.domain.dto;

import com.alex.consent.domain.enumeration.ChannelName;
import com.alex.consent.domain.enumeration.ConsentType;

import java.util.Objects;

public class ConsentDTO {

    private ConsentType type;
    private ChannelName channelName;
    private boolean consent;

    public ConsentDTO(ConsentType type, ChannelName channelName) {
        this.type = type;
        this.channelName = channelName;
    }

    public ConsentDTO(ConsentType type, ChannelName channelName, boolean consent) {
        this.type = type;
        this.channelName = channelName;
        this.consent = consent;
    }

    public void doConsent() {
        this.consent = true;
    }

    public ConsentType getType() {
        return type;
    }

    public void setType(ConsentType type) {
        this.type = type;
    }

    public ChannelName getChannelName() {
        return channelName;
    }

    public void setChannelName(ChannelName channelName) {
        this.channelName = channelName;
    }

    public boolean isConsent() {
        return consent;
    }

    public void setConsent(boolean consent) {
        this.consent = consent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConsentDTO that = (ConsentDTO) o;
        return consent == that.consent &&
                type == that.type &&
                channelName == that.channelName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, channelName, consent);
    }

    @Override
    public String toString() {
        return "ConsentDTO{" +
                "type=" + type +
                ", channelName=" + channelName +
                ", consent=" + consent +
                '}';
    }
}
