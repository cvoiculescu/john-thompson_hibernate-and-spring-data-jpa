package org.voiculescu.sdjpainheritance.domain.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Dolphin extends Mammal {
    private Boolean hasSpots;
}
