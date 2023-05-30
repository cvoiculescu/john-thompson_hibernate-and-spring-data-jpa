package org.voiculescu.sdjpajdbctemplate.entity;

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

    @OneToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Book(String title, String publisher, String isbn, Author author) {
        this.title = title;
        this.publisher = publisher;
        this.isbn = isbn;
        this.author = author;
    }
}
