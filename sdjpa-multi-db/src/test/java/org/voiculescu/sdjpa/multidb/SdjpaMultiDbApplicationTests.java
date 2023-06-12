package org.voiculescu.sdjpa.multidb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.voiculescu.sdjpa.multidb.domain.creditcard.CreditCard;
import org.voiculescu.sdjpa.multidb.services.CreditCardService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SdjpaMultiDbApplicationTests {

    @Autowired
    CreditCardService creditCardService;

    @Test
    @Rollback(value = false)
    void testSaveAndGetCreditCard() {
        CreditCard cc = CreditCard.builder()
                .firstName("Corneliu")
                .lastName("Voiculescu")
                .zipCode("12345")
                .creditCardNumber("12345678900000")
                .cvv("123")
                .expirationDate("08/2025")
                .build();
        CreditCard savedCC = creditCardService.saveCreditCard(cc);
        assertThat(savedCC).isNotNull();
        assertThat(savedCC.getId()).isNotNull();
        assertThat(savedCC.getCreditCardNumber()).isNotNull();

    }

}
