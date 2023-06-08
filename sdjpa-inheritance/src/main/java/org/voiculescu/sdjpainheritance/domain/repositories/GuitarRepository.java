package org.voiculescu.sdjpainheritance.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.voiculescu.sdjpainheritance.domain.jointable.Guitar;

public interface GuitarRepository extends JpaRepository<Guitar, Long> {
}