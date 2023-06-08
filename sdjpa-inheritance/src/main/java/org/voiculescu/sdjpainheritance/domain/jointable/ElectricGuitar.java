package org.voiculescu.sdjpainheritance.domain.jointable;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ElectricGuitar extends Guitar {
    private Integer numberOfPickups;
}
