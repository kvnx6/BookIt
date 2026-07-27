package com.bookit.bookit.business.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

@Getter @Setter
@NoArgsConstructor
public class CreateBusinessDTO {

    @NotBlank
    @Size(max = 255, message = "Your business name must not exceed 255 characters")
    private String name;

    @NotBlank @Size(min = 0, max = 255, message = "Your Business name URL must be between 0 and 255 characters")
    @Pattern(regexp = "^[a-z0-9]+(-[a-z0-9]+)*$", message = "URL must contain only lowercase letters, numbers, and dashes")
    private String urlName;

    @NotBlank
    @Size(max = 500, message = "Your business description must not exceed 500 characters")
    private String description;

    @NotBlank
    @Pattern(regexp = "^.*[0-9]+.*$", message = "Please include a house number in the address")
    private String address;

    @NotBlank
    @Size(max = 100, message = "City must not exceed 100 characters")
    private String city;

    @NotNull(message = "Please select a category")
    private Integer categoryId;

    @NotNull(message = "Please select an owner")
    private Integer OwnerId;
}
