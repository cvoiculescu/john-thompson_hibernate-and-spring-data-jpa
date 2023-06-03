package org.voiculescu.sdjpaspringdataqueries.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.voiculescu.sdjpaspringdataqueries.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByFirstNameLikeIgnoreCaseAndLastNameLikeIgnoreCase(String firstName, String lastName);





}
