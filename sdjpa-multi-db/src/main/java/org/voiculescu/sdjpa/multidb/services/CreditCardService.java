package org.voiculescu.sdjpa.multidb.services;

import org.voiculescu.sdjpa.multidb.domain.creditcard.CreditCard;

/**
 * Created by jt on 7/1/22.
 */
public interface CreditCardService {

    CreditCard getCreditCardById(Long id);

    CreditCard saveCreditCard(CreditCard cc);
}
