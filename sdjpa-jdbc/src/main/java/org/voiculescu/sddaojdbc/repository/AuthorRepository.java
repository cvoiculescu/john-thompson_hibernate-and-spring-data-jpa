package org.voiculescu.sddaojdbc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.voiculescu.sddaojdbc.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}