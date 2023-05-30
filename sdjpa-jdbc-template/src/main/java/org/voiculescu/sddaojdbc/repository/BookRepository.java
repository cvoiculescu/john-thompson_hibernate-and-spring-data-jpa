package org.voiculescu.sddaojdbc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.voiculescu.sddaojdbc.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}