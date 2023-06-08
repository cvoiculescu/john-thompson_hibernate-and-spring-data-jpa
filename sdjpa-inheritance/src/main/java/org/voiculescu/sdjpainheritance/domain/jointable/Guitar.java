package org.voiculescu.sdjpainheritance.domain.jointable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Guitar extends Instrument {

    private Integer numberOfStrings;

}
