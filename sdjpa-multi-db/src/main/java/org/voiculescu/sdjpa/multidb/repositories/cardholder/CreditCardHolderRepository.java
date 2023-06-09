package org.voiculescu.sdjpa.multidb.repositories.cardholder;

import org.voiculescu.sdjpa.multidb.domain.cardholder.CreditCardHolder;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CreditCardHolderRepository extends JpaRepository<CreditCardHolder, Long> {
}
