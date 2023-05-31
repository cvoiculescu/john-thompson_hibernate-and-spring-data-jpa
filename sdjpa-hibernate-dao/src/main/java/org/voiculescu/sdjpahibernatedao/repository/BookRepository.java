package org.voiculescu.sdjpahibernatedao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.voiculescu.sdjpahibernatedao.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
