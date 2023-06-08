package org.voiculescu.sdjpainheritance.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.voiculescu.sdjpainheritance.domain.jointable.Piano;

public interface PianoRepository extends JpaRepository<Piano, Long> {
}