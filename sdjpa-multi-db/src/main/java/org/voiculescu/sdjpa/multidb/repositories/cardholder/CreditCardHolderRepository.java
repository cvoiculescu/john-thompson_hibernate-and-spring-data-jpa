package org.voiculescu.sdjpa.multidb.repositories.cardholder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.voiculescu.sdjpa.multidb.domain.cardholder.CreditCardHolder;

import java.util.Optional;


public interface CreditCardHolderRepository extends JpaRepository<CreditCardHolder, Long> {

    public Optional<CreditCardHolder> findByCreditCardId(Long creditCardId);
}
