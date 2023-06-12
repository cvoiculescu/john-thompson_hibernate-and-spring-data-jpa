package org.voiculescu.sdjpa.multidb.domain.pan;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.voiculescu.sdjpa.multidb.domain.CreditCardConverter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@Entity(name = "credit_card_pan")
public class CreditCardPAN {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = CreditCardConverter.class)
    private String creditCardNumber;

    private Long creditCardId;
}
