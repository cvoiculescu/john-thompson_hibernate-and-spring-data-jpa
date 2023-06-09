package org.voiculescu.sdjpa.multidb.repositories.pan;

import org.voiculescu.sdjpa.multidb.domain.pan.CreditCardPAN;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardPANRepository extends JpaRepository<CreditCardPAN, Long> {
}
