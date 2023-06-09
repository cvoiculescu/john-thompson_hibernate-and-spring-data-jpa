package org.voiculescu.sdjpa.multidb.repositories.creditcard;

import org.voiculescu.sdjpa.multidb.domain.creditcard.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
