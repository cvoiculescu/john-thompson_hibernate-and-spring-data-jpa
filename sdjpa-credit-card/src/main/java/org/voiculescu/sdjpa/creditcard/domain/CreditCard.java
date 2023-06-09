package org.voiculescu.sdjpa.creditcard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.voiculescu.sdjpa.creditcard.interceptors.EncryptedString;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Slf4j
//@EntityListeners(CreditCardJPACallback.class)

public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @EncryptedString
    @Convert(converter = CreditCardConverter.class)
    private String creditCardNumber;

    private String cvv;

    private String expirationDate;

}
