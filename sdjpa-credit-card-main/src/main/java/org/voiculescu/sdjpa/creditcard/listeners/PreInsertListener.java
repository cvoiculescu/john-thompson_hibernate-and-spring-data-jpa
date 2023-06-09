package org.voiculescu.sdjpa.creditcard.listeners;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.springframework.stereotype.Component;
import org.voiculescu.sdjpa.creditcard.services.EncryptionService;

@Slf4j
@Component
public class PreInsertListener extends AbstractEncryptionListener implements PreInsertEventListener {

    public PreInsertListener(EncryptionService encryptionService) {
        super(encryptionService);
    }

    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        log.info("preInsert event");
        encrypt(event.getState(), event.getPersister().getPropertyNames(), event.getEntity());
        return false;
    }

}
