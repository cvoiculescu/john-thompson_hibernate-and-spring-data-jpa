package org.voiculescu.sdjpa.multidb.domain.creditcard;

import org.voiculescu.sdjpa.multidb.domain.CreditCardConverter;
import jakarta.persistence.*;

@Entity
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = CreditCardConverter.class)
    private String cvv;

    @Convert(converter = CreditCardConverter.class)
    private String expirationDate;

}









