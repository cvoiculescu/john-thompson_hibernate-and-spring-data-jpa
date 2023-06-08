package org.voiculescu.sdjpainheritance.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.voiculescu.sdjpainheritance.domain.jointable.Instrument;

public interface InstrumentRepository extends JpaRepository<Instrument, Long> {
}