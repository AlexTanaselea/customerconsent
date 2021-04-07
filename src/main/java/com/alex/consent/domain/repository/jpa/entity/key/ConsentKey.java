package com.alex.consent.domain.repository.jpa.entity.key;

import com.alex.consent.domain.enumeration.ChannelName;
import com.alex.consent.domain.enumeration.ConsentType;

import java.io.Serializable;
import java.util.Objects;

public class ConsentKey implements Serializable {

    private Long customerId;
    private ConsentType type;
    private ChannelName channelName;

    public ConsentKey() {
    }

    public ConsentKey(Long customerId, ConsentType type, ChannelName channelName) {
        this.customerId = customerId;
        this.type = type;
        this.channelName = channelName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConsentKey that = (ConsentKey) o;
        return Objects.equals(customerId, that.customerId) &&
                type == that.type &&
                channelName == that.channelName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, type, channelName);
    }
}
