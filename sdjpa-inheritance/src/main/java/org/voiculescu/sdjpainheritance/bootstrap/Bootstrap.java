package org.voiculescu.sdjpainheritance.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.voiculescu.sdjpainheritance.domain.jointable.ElectricGuitar;
import org.voiculescu.sdjpainheritance.domain.repositories.InstrumentRepository;

@Component
public class Bootstrap implements CommandLineRunner {

    final
    InstrumentRepository repository;

    public Bootstrap(InstrumentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        ElectricGuitar electricGuitar = new ElectricGuitar();
        electricGuitar.setNumberOfStrings(4);
        electricGuitar.setNumberOfPickups(16);
        repository.save(electricGuitar);
    }
}
