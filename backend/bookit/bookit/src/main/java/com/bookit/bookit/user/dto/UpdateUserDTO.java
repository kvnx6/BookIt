package com.bookit.bookit.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UpdateUserDTO {
    @Email(message = "please enter a valid email")
    @Size(min=1, message = "Your Email cannot be less than 1 Character") @Size(max=255, message = "Your Email cannot be more than 255 Character")
    private String email;

    @Size(max=255, message = "Your Name must not have more than 255 Character please use a nickname")
    @NotBlank
    private String name;

    @Size(max=255, message = "Your Surname must not have more than 255 Character please use a nickname")
    @NotBlank
    private String surname;
}
