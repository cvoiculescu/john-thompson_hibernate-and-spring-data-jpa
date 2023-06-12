package org.voiculescu.sdjpa.multidb.domain.pan;

import jakarta.persistence.*;
import lombok.*;
import org.voiculescu.sdjpa.multidb.domain.CreditCardConverter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "credit_card_pan")
public class CreditCardPAN {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = CreditCardConverter.class)
    private String creditCardNumber;
}
