package org.voiculescu.sdjpa.multidb.domain.pan;

import jakarta.persistence.*;
import lombok.*;
import org.voiculescu.sdjpa.multidb.domain.CreditCardConverter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CreditCardPAN {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = CreditCardConverter.class)
    private String creditCardNumber;
}
