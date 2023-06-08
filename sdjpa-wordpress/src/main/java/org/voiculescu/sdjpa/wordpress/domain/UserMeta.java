package org.voiculescu.sdjpa.wordpress.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@Entity
@Table(name = "wp_usermeta",
        indexes = {
                @Index(name = "meta_key", columnList = "meta_key"),
                @Index(name = "user_id", columnList = "user_id")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "PRIMARY", columnNames = {"umeta_id"})
        })
public class UserMeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "umeta_id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Size(max = 255)
    @Column(name = "meta_key")
    private String metaKey;

    @Lob
    @Column(name = "meta_value")
    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    private String metaValue;

}