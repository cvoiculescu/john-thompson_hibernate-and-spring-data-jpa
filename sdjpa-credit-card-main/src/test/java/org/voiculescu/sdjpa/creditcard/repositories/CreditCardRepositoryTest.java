package org.voiculescu.sdjpa.creditcard.repositories;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.voiculescu.sdjpa.creditcard.domain.CreditCard;
import org.voiculescu.sdjpa.creditcard.services.EncryptionService;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CreditCardRepositoryTest {

    final String CREDIT_CARD = "12345678900000";
    final String CVV = "123";
    final String EXPIRATION_DATE = "12/2028";

    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    EncryptionService encryptionService;
    @Autowired
    JdbcTemplate jdbcTemplate;

    CreditCard testCreditCard;

    @BeforeEach
    void setUp() {
        testCreditCard = new CreditCard()
                .setCreditCardNumber(CREDIT_CARD)
                .setCvv(CVV)
                .setExpirationDate(EXPIRATION_DATE);
    }

    @Test
    void testSaveAndStoreCreditCard() {
        log.info("Saving CC to DB");
        CreditCard savedCC = creditCardRepository.saveAndFlush(testCreditCard);
        log.info("Fetching CC from DB");
        CreditCard fetchedCC = creditCardRepository.findById(savedCC.getId()).orElse(null);
        log.info("CC in FREE: {}", CREDIT_CARD);
        log.info("CC at REST: {}", encryptionService.encrypt(CREDIT_CARD));

        Map<String, Object> dbRow = jdbcTemplate.queryForMap("SELECT * FROM credit_card " +
                "WHERE id = " + savedCC.getId());
        String dbCardValue = (String) dbRow.get("credit_card_number");

        assertThat(savedCC.getCreditCardNumber()).isNotEqualTo(dbCardValue);
        assertThat(dbCardValue).isEqualTo(encryptionService.encrypt(CREDIT_CARD));

        assertThat(fetchedCC).isNotNull();
        assertThat(fetchedCC.getId()).isNotNull();
        assertThat(fetchedCC.getCreditCardNumber()).isEqualTo(CREDIT_CARD);
        assertThat(fetchedCC.getCvv()).isEqualTo(CVV);
        assertThat(fetchedCC.getExpirationDate()).isEqualTo(EXPIRATION_DATE);
    }

}