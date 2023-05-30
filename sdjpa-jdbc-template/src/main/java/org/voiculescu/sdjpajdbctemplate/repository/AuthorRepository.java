package org.voiculescu.sdjpajdbctemplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.voiculescu.sdjpajdbctemplate.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
