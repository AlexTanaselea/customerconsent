package com.alex.consent.domain.repository.jpa.entity;

import com.alex.consent.domain.enumeration.ChannelName;
import com.alex.consent.domain.enumeration.ConsentType;
import com.alex.consent.domain.repository.jpa.entity.key.ConsentKey;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@Table(name = "consents")
@IdClass(ConsentKey.class)
public class Consent {

    @Id
    @Column(name = "customer_id")
    private Long customerId;

    @Id
    @Enumerated(EnumType.STRING)
    private ConsentType type;

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "channel_name")
    private ChannelName channelName;

    private boolean consent;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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
}
