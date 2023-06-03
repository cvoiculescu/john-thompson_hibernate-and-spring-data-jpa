package org.voiculescu.sdjpaspringdataqueries.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.voiculescu.sdjpaspringdataqueries.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
