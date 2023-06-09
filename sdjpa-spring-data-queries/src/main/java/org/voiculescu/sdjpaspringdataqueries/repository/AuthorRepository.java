package org.voiculescu.sdjpaspringdataqueries.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.voiculescu.sdjpaspringdataqueries.entity.Author;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByFirstNameLikeIgnoreCaseAndLastNameLikeIgnoreCase(String firstName, String lastName);





}
