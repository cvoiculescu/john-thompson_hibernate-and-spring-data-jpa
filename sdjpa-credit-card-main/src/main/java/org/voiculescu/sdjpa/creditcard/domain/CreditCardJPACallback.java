package org.voiculescu.sdjpa.creditcard.domain;

import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.voiculescu.sdjpa.creditcard.config.SpringContextHelper;
import org.voiculescu.sdjpa.creditcard.services.EncryptionService;

@Slf4j
public class CreditCardJPACallback {

    @PrePersist
    @PreUpdate
    public void beforeUpdateOrInsert(CreditCard creditCard) {
        creditCard.setCreditCardNumber(getEncryptionService().encrypt(creditCard.getCreditCardNumber()));
    }

    @PostPersist
    @PostLoad
    @PostUpdate
    public void postLoad(CreditCard creditCard) {
        creditCard.setCreditCardNumber(getEncryptionService().decrypt(creditCard.getCreditCardNumber()));
    }

    private EncryptionService getEncryptionService() {
        return SpringContextHelper.getApplicationContext()
                .getBean(EncryptionService.class);
    }

}
