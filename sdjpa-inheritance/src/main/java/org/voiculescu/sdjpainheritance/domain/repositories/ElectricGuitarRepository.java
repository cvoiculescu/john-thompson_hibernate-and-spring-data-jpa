package org.voiculescu.sdjpainheritance.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.voiculescu.sdjpainheritance.domain.jointable.ElectricGuitar;

public interface ElectricGuitarRepository extends JpaRepository<ElectricGuitar, Long> {
}