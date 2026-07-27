package com.bookit.bookit.business;

import com.bookit.bookit.category.Category;
import com.bookit.bookit.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Accessors(chain = true)
@Entity @Table(name = "businesses")
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotBlank @Size(min = 0, max = 255, message = "Your Business name must be between 0 and 255 characters")
    private String name;

    @Column(nullable = false, name = "url_name", unique = true, comment = "url must be unique")
    @NotBlank @Size(min = 0, max = 255, message = "Your Business name URL must be between 0 and 255 characters")
    @Pattern(regexp = "^[a-z0-9]+(-[a-z0-9]+)*$", message = "URL must contain only lowercase letters, numbers, and dashes")
    private String urlName;

    @Column(nullable = false)
    @NotBlank @Size(min = 0, max = 500, message = "Your Business description must be between 0 and 500 characters")
    private String description;

    @Column(nullable = false)
    @NotBlank @Pattern(regexp = "^.*[0-9]+.*$", message = "Please include a house number in the address")
    private String address;

    @Column(nullable = false)
    @NotBlank
    private String city;

    @Column(nullable = false, name = "created_at")
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
