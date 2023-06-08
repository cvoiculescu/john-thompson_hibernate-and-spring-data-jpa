package org.voiculescu.sdjpa.wordpress.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@Entity
@Table(name = "wp_commentmeta",
        indexes = {
                @Index(name = "comment_id", columnList = "comment_id"),
                @Index(name = "meta_key", columnList = "meta_key")
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"meta_id"})
        })
public class CommentMeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meta_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Size(max = 255)
    @Column(name = "meta_key")
    private String metaKey;

    @Lob
    @Column(name = "meta_value")
    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    private String metaValue;

}