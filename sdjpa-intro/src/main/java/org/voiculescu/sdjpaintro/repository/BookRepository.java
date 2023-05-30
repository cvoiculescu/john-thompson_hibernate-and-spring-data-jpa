package org.voiculescu.sdjpaintro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.voiculescu.sdjpaintro.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}