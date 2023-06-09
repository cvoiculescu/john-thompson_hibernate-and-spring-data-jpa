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
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @EncryptedString
    private String creditCardNumber;

    private String cvv;

    private String expirationDate;

    @PrePersist
    public void prePersist() {
        log.info("JPA PrePersist");
    }
}
