package org.voiculescu.sdjpa.creditcard.domain;

import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreditCardJPACallback {
    @PrePersist
    @PreUpdate
    public void beforeUpdateOrInsert(CreditCard creditCard) {
        log.info("Listener Insert Or Update JPA Callback");
    }

    @PostPersist
    @PostLoad
    @PostUpdate
    public void postLoad(CreditCard creditCard) {
        log.info("Listener Load JPA Callback");
    }
}
