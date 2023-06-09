package org.voiculescu.sdjpa.creditcard.repositories;

import org.springframework.stereotype.Repository;
import org.voiculescu.sdjpa.creditcard.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
