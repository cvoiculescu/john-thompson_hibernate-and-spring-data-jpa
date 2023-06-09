package org.voiculescu.sdjpa.creditcard.listeners;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;
import org.voiculescu.sdjpa.creditcard.services.EncryptionService;

@Slf4j
@Component
public class PreUpdateListener extends AbstractEncryptionListener implements PreUpdateEventListener {

    public PreUpdateListener(EncryptionService encryptionService) {
        super(encryptionService);
    }

    @Override
    public boolean onPreUpdate(PreUpdateEvent event) {
        log.info("preUpdate event");
        encrypt(event.getState(), event.getPersister().getPropertyNames(), event.getEntity());
        return false;
    }
}
