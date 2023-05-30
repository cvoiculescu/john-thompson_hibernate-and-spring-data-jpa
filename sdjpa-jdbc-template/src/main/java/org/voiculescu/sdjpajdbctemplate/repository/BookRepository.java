package org.voiculescu.sdjpajdbctemplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.voiculescu.sdjpajdbctemplate.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
