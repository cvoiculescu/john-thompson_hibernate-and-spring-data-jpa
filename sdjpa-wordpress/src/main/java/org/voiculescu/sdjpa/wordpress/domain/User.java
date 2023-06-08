package org.voiculescu.sdjpa.wordpress.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "wp_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Size(max = 60)
    @NotNull
    @Column(name = "user_login", nullable = false, length = 60)
    private String login;

    @Size(max = 255)
    @NotNull
    @Column(name = "user_pass", nullable = false)
    private String password;

    @Size(max = 50)
    @NotNull
    @Column(name = "user_nicename", nullable = false, length = 50)
    private String nicename;

    @Size(max = 100)
    @NotNull
    @Column(name = "user_email", nullable = false, length = 100)
    private String email;

    @Size(max = 100)
    @NotNull
    @Column(name = "user_url", nullable = false, length = 100)
    private String url;

    @NotNull
    @Column(name = "user_registered", nullable = false)
    private Timestamp registered;

    @Size(max = 255)
    @NotNull
    @Column(name = "user_activation_key", nullable = false)
    private String activationKey;

    @NotNull
    @Column(name = "user_status", nullable = false)
    private Integer status;

    @Size(max = 250)
    @NotNull
    @Column(name = "display_name", nullable = false, length = 250)
    private String displayName;

}