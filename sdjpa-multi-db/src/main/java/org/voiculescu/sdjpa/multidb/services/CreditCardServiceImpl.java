package org.voiculescu.sdjpa.multidb.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.voiculescu.sdjpa.multidb.domain.cardholder.CreditCardHolder;
import org.voiculescu.sdjpa.multidb.domain.creditcard.CreditCard;
import org.springframework.stereotype.Service;
import org.voiculescu.sdjpa.multidb.domain.pan.CreditCardPAN;
import org.voiculescu.sdjpa.multidb.repositories.card.CreditCardRepository;
import org.voiculescu.sdjpa.multidb.repositories.cardholder.CreditCardHolderRepository;
import org.voiculescu.sdjpa.multidb.repositories.pan.CreditCardPANRepository;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRepository creditCardRepository;
    private final CreditCardHolderRepository creditCardHolderRepository;
    private final CreditCardPANRepository creditCardPANRepository;


    @Override
    public CreditCard getCreditCardById(Long id) {
        CreditCard creditCard = creditCardRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        CreditCardPAN creditCardPAN = creditCardPANRepository.findByCreditCardId(id)
                .orElseThrow(EntityNotFoundException::new);
        CreditCardHolder creditCardHolder = creditCardHolderRepository.findByCreditCardId(id)
                .orElseThrow(EntityNotFoundException::new);
        creditCard.setCreditCardNumber(creditCardPAN.getCreditCardNumber())
                .setFirstName(creditCardHolder.getFirstName())
                .setLastName(creditCardHolder.getLastName())
                .setZipCode(creditCardHolder.getZipCode());
        return creditCard;
    }

    @Override
    @Transactional
    public CreditCard saveCreditCard(CreditCard cc) {
        CreditCard savedCC = creditCardRepository.save(cc)
                .setFirstName(cc.getFirstName())
                .setLastName(cc.getFirstName())
                .setZipCode(cc.getZipCode())
                .setCreditCardNumber(cc.getCreditCardNumber());
        creditCardHolderRepository.save(CreditCardHolder.builder()
                .creditCardId(savedCC.getId())
                .firstName(cc.getFirstName())
                .lastName(cc.getLastName())
                .zipCode(cc.getZipCode())
                .build());
        creditCardPANRepository.save(CreditCardPAN.builder()
                .creditCardId(savedCC.getId())
                .creditCardNumber(savedCC.getCreditCardNumber())
                .build()
        );
        return savedCC;
    }
}
