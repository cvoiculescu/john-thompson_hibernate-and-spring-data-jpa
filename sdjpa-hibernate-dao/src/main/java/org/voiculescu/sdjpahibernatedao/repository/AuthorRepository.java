package org.voiculescu.sdjpahibernatedao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.voiculescu.sdjpahibernatedao.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
