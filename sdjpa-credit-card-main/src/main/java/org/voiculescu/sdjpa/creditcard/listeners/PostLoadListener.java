package org.voiculescu.sdjpa.creditcard.listeners;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.event.spi.PostLoadEvent;
import org.hibernate.event.spi.PostLoadEventListener;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.springframework.stereotype.Component;
import org.voiculescu.sdjpa.creditcard.services.EncryptionService;

@Slf4j
@Component
public class PostLoadListener extends AbstractEncryptionListener implements PostLoadEventListener {

    public PostLoadListener(EncryptionService encryptionService) {
        super(encryptionService);
    }

    @Override
    public void onPostLoad(PostLoadEvent event) {
        log.info("postLoad Event\"");
        decrypt(event.getEntity());
    }
}
