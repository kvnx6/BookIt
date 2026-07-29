package com.bookit.bookit.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Accessors(chain = true)
@Table(name = "users") @Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Email(message = "please enter a valid email")
    @Column(nullable = false, unique = true)
    @Size(min=1, message = "Your Email cannot be less than 1 Character") @Size(max=255, message = "Your Email cannot be more than 255 Character")
    private String email;

    @Column(nullable = false, name = "password_hash")
    @Size(min=6, message = "Your Password cannot have less than 6 Character") @Size(max=255, message = "Your Password cannot have more than 255 Character")
    @NotBlank
    private String password;

    @Column(nullable = false)
    @Size(max=255, message = "Your Name must not have more than 255 Character please use a nickname")
    @NotBlank
    private String name;

    @Column(nullable = false)
    @Size(max=255, message = "Your Surname must not have more than 255 Character please use a nickname")
    @NotBlank
    private String surname;

    @Column(nullable = false, name = "created_at")
    private LocalDate createdAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }
}
