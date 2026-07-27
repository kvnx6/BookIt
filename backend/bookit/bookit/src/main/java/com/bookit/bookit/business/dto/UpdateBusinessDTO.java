package com.bookit.bookit.business.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class UpdateBusinessDTO {
    @NotNull @NotBlank
    @Size(min = 0, max = 500, message = "Your Business description must be between 0 and 500 characters")
    private String description;

    @NotBlank @NotNull
    @Pattern(regexp = "^.*[0-9]+.*$", message = "Please include a house number in the address")
    private String address;

    @NotBlank @NotNull
    private String city;
}
