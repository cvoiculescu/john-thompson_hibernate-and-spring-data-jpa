package org.voiculescu.sddaojdbc.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;
    private String publisher;
    private String isbn;
    private String author_id;

    public Book(String title, String publisher, String isbn, String author_id) {
        this.title = title;
        this.publisher = publisher;
        this.isbn = isbn;
        this.author_id = author_id;
    }
}
